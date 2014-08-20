package com.storeassistant.adapter;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class ViewPagerAdapter extends PagerAdapter {

	private ArrayList<View> mListViews; 
	private List<String> titles;
	
	public ViewPagerAdapter(ArrayList<View> mListViews) {
		super();
		this.mListViews = mListViews;
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		// TODO Auto-generated method stub
		((ViewPager)arg0).removeView(mListViews.get(arg1 % mListViews.size()));
	}

	@Override
	public void finishUpdate(View arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1000;
	}

	@Override
	public Object instantiateItem(View arg0, int arg1) {
		// TODO Auto-generated method stub
		View view = mListViews.get(arg1 % mListViews.size());
		((ViewPager)arg0).addView(view);
		return view;

	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
		// TODO Auto-generated method stub

	}
	

}
