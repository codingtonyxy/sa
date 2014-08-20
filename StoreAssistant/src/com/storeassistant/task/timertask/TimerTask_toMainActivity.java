package com.storeassistant.task.timertask;

import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.os.Message;

import com.storeassistant.activity.home.MainActivity;
import com.storeassistant.activity.launcher.StartActivity;
import com.storeassistant.appInfo.MyConstants;

/**
 * 倒计时后进入主页
 * @author Administrator
 *
 */
public class TimerTask_toMainActivity extends TimerTask{
	
	private Context context = null;
	private long cTime = 0;
    public TimerTask_toMainActivity(Context context){
    	this.context = context;
    	this.cTime = System.currentTimeMillis();
    }
	@Override
	public void run() {
		try {
			//显示倒计时文字
			StartActivity sa = (StartActivity)context;
			if(sa.msg_showCount >= 0){
				Message msg = sa.handler_toIndex.obtainMessage();
				msg.what = sa.msg_showTimer;
				sa.handler_toIndex.sendMessage(msg);
				return;
			}
			
			long now = System.currentTimeMillis();
			long delayTime = now - cTime;
			//跳转进入主页
			if(delayTime >= MyConstants.DELAY_TO_INDEX && context != null){
				sa.myTimer.cancel();
				Intent intent = new Intent(context, MainActivity.class);
				context.startActivity(intent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
