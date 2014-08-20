package com.storeassistant.activity.launcher;

import java.util.Timer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.storeassistant.R;
import com.storeassistant.activity.base.BaseActivity;
import com.storeassistant.appInfo.ImageUrl;
import com.storeassistant.appInfo.MyConstants;
import com.storeassistant.listener.StartImageIistener;
import com.storeassistant.task.timertask.TimerTask_toMainActivity;
import com.storeassistant.util.TimerUtil;

/**
 * 
 * @author codingtony
 * �������棺һ��Ϊ�������ҳ��
 */
public class StartActivity extends BaseActivity{
	
	public final int msg_showTimer = 1;//��ʾ����ʱ����
	public int msg_showCount = MyConstants.MSG_SHOW_COUNT_TO_INDEX;
	public Handler handler_toIndex = null;
	public Timer myTimer = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		
		ImageLoader imageLoader = ImageLoader.getInstance();
		ImageLoaderConfiguration conf = ImageLoaderConfiguration.createDefault(this);
		imageLoader.init(conf);
		
		ImageView startImageView = (ImageView)findViewById(R.id.img_start);
		imageLoader.displayImage(ImageUrl.IMAGE_START_URL, startImageView, new StartImageIistener());
		Log.i("StartActivity", "StartActivity onCrate end");
		
		//����ʱ3�룬������ҳ
		myTimer = TimerUtil.getTimer("toIndex");
		TimerUtil.schedule(myTimer, new TimerTask_toMainActivity(this), 1000, 1000);
		
		initHander();
	}

	private void initHander() {
		Looper looper = this.getMainLooper();
		handler_toIndex = new Handler(looper){
			public void dispatchMessage(Message msg){
				switch (msg.what) {
				case msg_showTimer:
					TextView textViewShowTimer = (TextView)findViewById(R.id.textView_showTimerToIndex);
					textViewShowTimer.setText("����ʱ"+msg_showCount+"��");
					msg_showCount --;
					break;

				default:
					break;
				}
			};
		};
		
		
	}
	
	

}