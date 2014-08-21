package com.storeassistant.activity.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.storeassistant.R;
import com.storeassistant.activity.home.fragment.FragmentMain;
import com.storeassistant.activity.home.fragment.FragmentMall;
import com.storeassistant.activity.home.fragment.FragmentNearBy;
import com.storeassistant.activity.home.fragment.FragmentPcenter;
import com.storeassistant.appInfo.MyConstants;

public class MainActivity extends FragmentActivity{

	private FragmentManager fragmentManager = null;
	private RadioGroup radioGroup = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		MyConstants.width = wm.getDefaultDisplay().getWidth();
		MyConstants.height = wm.getDefaultDisplay().getHeight();
		
		fragmentManager = getSupportFragmentManager();
		radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);
        //Ä¬ÈÏ¼ÓÔØÖ÷Ò³
        addFragment(R.id.main_tab_cb_home);
	}
	
	public void addFragment(int vid){
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Fragment fragment = null;
		switch (vid) {
		case R.id.main_tab_cb_home:
			fragment = new FragmentMain();
			break;
		case R.id.main_tab_cb_nearby:
			fragment = new FragmentNearBy();
			break;
		case R.id.main_tab_cb_shop:
			fragment = new FragmentMall();
			break;
		case R.id.main_tab_cb_pcenter:
			fragment = new FragmentPcenter();
			break;

		default:
			break;
		}
		LinearLayout layout = (LinearLayout)findViewById(R.id.container_fragment);
		layout.removeAllViews();
		if(fragment != null){
			if(!fragment.isAdded()){
				fragmentTransaction.add(R.id.container_fragment, fragment);
				fragmentTransaction.commit();
			}
		}
	}
	
	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			addFragment(checkedId);
		}
	};
		




}
