#!/usr/bin/python

import socket
import time, threading
import mysql.connector

conn = mysql.connector.connect(user='root', password='12330321', database='test', use_unicode=True)
cursor = conn.cursor()

def SignIn(sock, data_info):
	cursor.execute('select password from users where binary name = %s', [data_info[1]])
	ps = cursor.fetchone()
	if ps:
		if ps[0] == data_info[2]:
			sock.send('Hello, %s!' % data_info[1])
		else:
			sock.send('Wrong password')
	else:
		sock.send('User %s doesn\'t exsit' % data_info[1])

def SignUp(sock, data_info):
	cursor.execute('select * from users where binary name = %s limit 1', [data_info[1]])
	cursor.fetchone()
	if cursor.rowcount == 1:
		sock.send('User %s has exsited' % data_info[1])
	else:
		cursor.execute('insert into users (name, password) values (%s, %s)', [data_info[1], data_info[2]])
		conn.commit()
		sock.send('Signup Succeed!\n')

def Load(sock, data_info):
	cursor.execute('select * from plans')
	data = cursor.fetchall()
	num = (int)(data_info[1])
	for item in data[:num]:
		msg = item[0]+':'+item[1]+':'+item[2]
		sock.send(msg)
	sock.send('EOF')

def Store(sock, data_info):
	cursor.execute('insert into plans (user, date, content) values (%s, %s, %s)', [data_info[1], data_info[2]], data_info[3])
	conn.commit()
	re = cursor.fetchone()
	if re == 1:
		sock.send('Store Succeed!\n')
	else:
		sock.send('Store Failed!\n')

def tcplink(sock, addr):
	print 'accept new connection from %s:%s...' % addr
	sock.send('Welcome')

	while True:
		data = sock.recv(1024)
		time.sleep(1)
		if data == 'exit' or not data:
			break
		data_info = data.split(':')
		if data_info[0] == 'SignIn':
			SignIn(sock, data_info)
		if data_info[0] == 'SignUp':
			SignUp(sock, data_info)
		if data_info[0] == 'Load':
			Load(sock, data_info)
		if data_info[0] == 'Store':
			Store(sock, data_info)

	sock.close()
	print 'Connection from %s:%s closed.' % addr


s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind(('172.18.35.75', 9998))
s.listen(10)
print 'Waiting for connection...'

while True:
	sock, addr = s.accept()
	t = threading.Thread(target=tcplink, args=(sock, addr))
	t.start()
