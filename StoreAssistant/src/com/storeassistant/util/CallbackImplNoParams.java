package com.storeassistant.util;
import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.widget.ImageView;


public class CallbackImplNoParams implements AsyImageLoaderNoParams.ImageCallback {
	private ImageView iv;
	private final WeakReference<ImageView> imageViewReference;//������
	
	public CallbackImplNoParams(ImageView iv) {
		super();
		this.iv = iv;	
		imageViewReference=new WeakReference<ImageView>(iv);//�Ƚ�ImageView����������
	}

	
	public void imageloader(Bitmap imageDrawable) {
		if(imageViewReference!=null){
			ImageView imageView=imageViewReference.get();//����������ȡ��ImageView
			if(imageView!=null){
				if(imageDrawable!=null){
					try {
						iv.setImageBitmap(imageDrawable);	
					} catch (Exception e) {
						System.out.println("no bitmap...");
					}
					
				}
			}
		}
	
	}
	
	
}
