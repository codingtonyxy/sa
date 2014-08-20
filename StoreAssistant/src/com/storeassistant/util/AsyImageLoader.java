package com.storeassistant.util;


import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


/** ʵ��ͼƬ���첽���� ***/
public class AsyImageLoader {
	private Bitmap bitmap;
	private KuangUtils _ku=KuangUtils.getInstance();
	private Map<String, BitmapRef> imageCache = new HashMap<String, BitmapRef>();
	private ReferenceQueue<Bitmap> q=new ReferenceQueue<Bitmap>();//���ö���
	private String fileName;
	public Bitmap loadDrawable(final String imageUrl,
			final ImageCallback callback,final int width,final int height,final float corner) {
		if (imageCache.containsKey(imageUrl)) {
			BitmapRef sr = imageCache.get(imageUrl);
			// �������ͼƬû�б����
			if (sr.get() != null) {return sr.get();}
		}
		// û�����صĻ����ʹ�����������
		final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
		//��ͼƬд�뵽SD��
		fileName=imageUrl.substring(imageUrl.lastIndexOf("/")+1,imageUrl.length());
		if(_ku.enable==false){
		try {
			if(_ku.getImageFromSD(fileName)==null){
			//�����ظ�����,�˷���
			_ku.saveFile((Bitmap) msg.obj, fileName);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		callback.imageloader((Bitmap) msg.obj,width,height,corner);
		}};
		new Thread() {
		public void run() {
		bitmap = loadImageFromUrl(imageUrl);
		cleanCache();//���ö����е�bitmap��Ҫ��,���������յ�,�Ƚ���Ӽ������Ƴ�?
		BitmapRef ref=new BitmapRef(bitmap, q, imageUrl);
		imageCache.put(imageUrl, new BitmapRef(bitmap, q, imageUrl));
		Message msg = handler.obtainMessage(0, bitmap);
		handler.sendMessage(msg);}}.start();
		return bitmap;
	}
	// �������ϻ�ȡͼƬ
	public Bitmap loadImageFromUrl(String imageUrl) {
		Bitmap bitmap = null;

		try {
			URL url=new URL(imageUrl);
			InputStream is=url.openStream();
			/***���java.lang.OutOfMemoryError: bitmap size exceeds VM budget
			 * ����,ʹ�ò���ֵ,����ͼƬ̫�����ͼƬ̫��ʹ��BitmapFactory�������?
			 * �ô���,�ҵ����ʵĲ���ֵ**/
			BitmapFactory.Options options=new BitmapFactory.Options();
		    options.inDither=false;    /*������ͼƬ��������*/
		    options.inPreferredConfig=null;  /*�����ý���������ѷ�ʽ����?/
		    options.inSampleSize=3;          /*ͼƬ���?����С����*/
			bitmap = BitmapFactory.decodeStream(is,null,options);
		    is.close();
		} catch (Exception e) {}
		return bitmap;

	}
	

	// �ص�ӿ�?
	protected interface ImageCallback {
		public void imageloader(Bitmap imageDrawable,int width,int height,float corner);
	}
	
	private class BitmapRef extends SoftReference<Bitmap>{
		private String key="";
		//��Ĭ�ϵĸ��๹�����п������һ��?
		public BitmapRef(Bitmap r, ReferenceQueue<? super Bitmap> q,String key) {
			super(r, q);
			this.key=key;
		}	
	}
	
	/**
	 * method:�������з������ö��е�BitmapRef�Ƴ�<br>
	 * @return
	 */
	private void cleanCache() {
		// TODO Auto-generated method stub
		BitmapRef br=null;
		//��ѯ���ö���,q.poll()���п��õ����ö���,����,���ö�����Ϊ,�����������?
		while((br=(BitmapRef)q.poll())!=null){
			imageCache.remove(br.key);
		}
	}
}
