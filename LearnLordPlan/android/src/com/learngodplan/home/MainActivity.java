package com.learngodplan.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learngodplan.R;
import com.learngodplan.personal.FragmentPersonal;
import com.learngodplan.pet.FragmentPet;
import com.learngodplan.plan.FragmentPlan;
  
  
  
public class MainActivity extends FragmentActivity implements OnClickListener{  
  
    //定义4个Fragment的对象  
    private FragmentHome fgHome;  
    private FragmentPlan fgPlan;  
    private FragmentPet fgPet;
    private FragmentPersonal fgPersonal;
    
    //定义底部导航栏的三个布局  
    private RelativeLayout guideLayout_Home;  
    private RelativeLayout guideLayout_Plan;  
    private RelativeLayout guideLayout_Pet; 
    private RelativeLayout guideLayout_Personal;
    
    //定义底部导航栏中的ImageView与TextView  
    private ImageView guideImg_Home;  
    private ImageView guideImg_Plan;  
    private ImageView guideImg_Pet;  
    private ImageView guideImg_Personal;  
    
    private TextView guideText_Home;  
    private TextView guideText_Plan;  
    private TextView guideText_Pet;
    private TextView guideText_Personal;
    
    //定义要用的颜色值  
    private int whirt = 0xFFFFFFFF;  
    private int gray = 0xFF7597B3;  
    private int blue =0xFF0AB2FB;  
    
    public static Context mainAcContext;
    
    //定义FragmentManager对象  
    public static FragmentManager fManager;  
    
    //再按一次退出程序的按钮
    private long exitTime = 0;
	
    @Override  
    protected void onCreate(Bundle savedInstanceState) {      	
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        fManager = getSupportFragmentManager();  
        initViews();  
        mainAcContext = this;
        
        //默认显示home的fragment
        FragmentTransaction transaction = fManager.beginTransaction();    
        clearChioce();  
        hideFragments(transaction);  
        guideImg_Home.setImageResource(R.drawable.pressbottom_img_home);    
        guideText_Home.setTextColor(blue);  
        guideLayout_Home.setBackgroundResource(R.drawable.ic_tabbar_bg_click);  
        if (fgHome == null) {    
            // 如果fgHome为空，则创建一个并添加到界面上    
            fgHome = new FragmentHome();    
            transaction.add(R.id.content, fgHome);
            transaction.show(fgHome); 
        } else {    
            //如果MessageFragment不为空，则刷新它
            fgHome = new FragmentHome();   
        	transaction.replace(R.id.content, fgHome);
            transaction.show(fgHome);    
        }
        transaction.commit();
    }  
      
     
    
    
    //完成导航组件的初始化  
    public void initViews()  
    {  
        guideImg_Home = (ImageView) findViewById(R.id.guideImg_Home);  
        guideImg_Plan = (ImageView) findViewById(R.id.guideImg_Plan);  
        guideImg_Pet = (ImageView) findViewById(R.id.guideImg_Pet);
        guideImg_Personal = (ImageView) findViewById(R.id.guideImg_Personal);
        
        guideText_Home = (TextView) findViewById(R.id.guideText_Home);  
        guideText_Pet = (TextView) findViewById(R.id.guideText_Pet);  
        guideText_Plan = (TextView) findViewById(R.id.guideText_Plan); 
        guideText_Personal = (TextView)findViewById(R.id.guideText_Personal);
        
        guideLayout_Home = (RelativeLayout) findViewById(R.id.guideLayout_Home);  
        guideLayout_Plan = (RelativeLayout) findViewById(R.id.guideLayout_Plan);  
        guideLayout_Pet = (RelativeLayout) findViewById(R.id.guideLayout_Pet);  
        guideLayout_Personal = (RelativeLayout) findViewById(R.id.guideLayout_Personal);  
        
        guideLayout_Home.setOnClickListener(this);  
        guideLayout_Plan.setOnClickListener(this);   
        guideLayout_Pet.setOnClickListener(this);
        guideLayout_Personal.setOnClickListener(this);
    }  
      
    //重写onClick事件  
    @Override  
    public void onClick(View view) {  
        switch (view.getId()) {  
        case R.id.guideLayout_Home:  
            setChioceItem(0);  
            break;  
        case R.id.guideLayout_Plan:  
            setChioceItem(1);  
            break;  
        case R.id.guideLayout_Pet:  
            setChioceItem(2);  
            break;  
        case R.id.guideLayout_Personal:  
            setChioceItem(3);  
            break;  
            
        default:  
            break;  
        }  
          
    }  
      
      
    //定义一个选中一个item后的处理  
    public void setChioceItem(int index)  
    {  
        //重置选项+隐藏所有Fragment  
        FragmentTransaction transaction = fManager.beginTransaction();    
        clearChioce();  
        hideFragments(transaction);  
        switch (index) {  
        case 0:  
            guideImg_Home.setImageResource(R.drawable.pressbottom_img_home);    
            guideText_Home.setTextColor(blue);  
            guideLayout_Home.setBackgroundResource(R.drawable.ic_tabbar_bg_click);  
            if (fgHome == null) {    
                fgHome = new FragmentHome();    
                transaction.add(R.id.content, fgHome);    
            } else {    
                fgHome = new FragmentHome(); 
            	transaction.replace(R.id.content, fgHome);
                transaction.show(fgHome);    
            }    
            break;    
  
        case 1:  
            guideImg_Plan.setImageResource(R.drawable.pressbottom_img_plan);    
            guideText_Plan.setTextColor(blue);  
            guideLayout_Plan.setBackgroundResource(R.drawable.ic_tabbar_bg_click);  
            if (fgPlan == null) {    
                fgPlan = new FragmentPlan();    
                transaction.add(R.id.content, fgPlan);    
            } else {     
                fgPlan= new FragmentPlan(); 
            	transaction.replace(R.id.content, fgPlan);
                transaction.show(fgPlan);  
            }    
            break;        
          
         case 2:  
            guideImg_Pet.setImageResource(R.drawable.pressbottom_img_pet);    
            guideText_Pet.setTextColor(blue);  
            guideLayout_Pet.setBackgroundResource(R.drawable.ic_tabbar_bg_click);  
            if (fgPet == null) {      
                fgPet = new FragmentPet();    
                transaction.add(R.id.content, fgPet);    
            } else {    
                fgPet = new FragmentPet(); 
            	transaction.replace(R.id.content, fgPet);
                transaction.show(fgPet);  
            }    
            break;
            
         case 3:  
             guideImg_Personal.setImageResource(R.drawable.pressbottom_img_personal);    
             guideText_Personal.setTextColor(blue);  
             guideLayout_Personal.setBackgroundResource(R.drawable.ic_tabbar_bg_click);  
             if (fgPersonal == null) {      
                 fgPersonal = new FragmentPersonal();    
                 transaction.add(R.id.content, fgPersonal);    
             } else {     
                 fgPersonal = new FragmentPersonal(); 
             	 transaction.replace(R.id.content, fgPersonal);
                 transaction.show(fgPersonal);  
             }    
             break;     
        }  
        transaction.commit();  
    }  
      
    //隐藏所有的Fragment,避免fragment混乱  
    private void hideFragments(FragmentTransaction transaction) {    
        if (fgHome != null) {    
            transaction.hide(fgHome);    
        }    
        if (fgPlan != null) {    
            transaction.hide(fgPlan);    
        }    
        if (fgPet != null) {    
            transaction.hide(fgPet);    
        }
        if (fgPersonal != null) {    
            transaction.hide(fgPersonal);    
        }
    }    
              
    //定义一个重置所有选项的方法  
    public void clearChioce()  
    {  
        guideImg_Home.setImageResource(R.drawable.unpressbottom_img_home);  
        guideLayout_Home.setBackgroundColor(whirt);  
        guideText_Home.setTextColor(gray);
        
        guideImg_Plan.setImageResource(R.drawable.unpressbottom_img_plan);  
        guideLayout_Plan.setBackgroundColor(whirt);  
        guideText_Plan.setTextColor(gray);  
        
        guideImg_Pet.setImageResource(R.drawable.unpressbottom_img_pet);  
        guideLayout_Pet.setBackgroundColor(whirt);  
        guideText_Pet.setTextColor(gray);  
        
        guideImg_Personal.setImageResource(R.drawable.unpressbottom_img_personal);  
        guideLayout_Personal.setBackgroundColor(whirt);  
        guideText_Personal.setTextColor(gray);  
    } 
    
    //"再按一次退出程序"
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
		    if((System.currentTimeMillis()-exitTime) > 1000){
			    Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
			    exitTime = System.currentTimeMillis();
		    } 
		    else {
			    finish();
			    System.exit(0);
		    }
		    return true;
	    }
	    return super.onKeyDown(keyCode, event);
    } 
}