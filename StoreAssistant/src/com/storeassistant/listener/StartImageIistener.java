package com.storeassistant.listener;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

public class StartImageIistener implements ImageLoadingListener{

	@Override
	public void onLoadingCancelled(String s, View view) {
		Log.w("StartImageIistener", "onLoadingCancelled");
	}

	@Override
	public void onLoadingComplete(String s, View view, Bitmap bitmap) {
		Log.w("StartImageIistener", "onLoadingComplete");
	}

	@Override
	public void onLoadingFailed(String s, View view, FailReason failreason) {
		Log.w("StartImageIistener", "onLoadingFailed");
	}

	@Override
	public void onLoadingStarted(String s, View view) {
		Log.w("StartImageIistener", "onLoadingStarted");
	}

}
