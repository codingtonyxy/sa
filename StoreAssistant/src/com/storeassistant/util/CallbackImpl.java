package com.storeassistant.util;
import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;


public class CallbackImpl implements AsyImageLoader.ImageCallback {
	private ImageView iv;
	private final WeakReference<ImageView> imageViewReference;//������
	
	public CallbackImpl(ImageView iv) {
		super();
		this.iv = iv;	
		imageViewReference=new WeakReference<ImageView>(iv);//�Ƚ�ImageView����������
	}

	
	public void imageloader(Bitmap imageDrawable,int width,int height,float corner) {
		if(imageViewReference!=null){
			ImageView imageView=imageViewReference.get();//����������ȡ��ImageView
			imageView.setScaleType(ScaleType.CENTER_CROP);
			if(imageView!=null){
				if(imageDrawable!=null){
					try {
						imageDrawable=KuangUtils.zoomImage(imageDrawable, width,height);
					} catch (Exception e) {
						System.out.println("no bitmap...");
					}
					imageView.setImageBitmap(KuangUtils.convertToRoundedCorner(imageDrawable,corner));
				}
			}
		}
	
	}
	
	
}
