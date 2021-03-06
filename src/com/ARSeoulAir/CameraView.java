package com.ARSeoulAir;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class Preview extends SurfaceView implements SurfaceHolder.Callback { 
	SurfaceHolder mHolder; 
	Camera mCamera; 

	boolean mPreviewRunning = false;

	Preview(Context context) { 
		super(context); 
		mHolder = getHolder(); 
		mHolder.addCallback(this); 
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 

	} 

	Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
		public void onPictureTaken(byte[] data, Camera c) {
			mCamera.startPreview();
		}
	};

	public void surfaceCreated(SurfaceHolder holder) 
	{
		try
		{
			mCamera = Camera.open(); 
			try{
				mCamera.setPreviewDisplay(holder); 
			}catch (IOException e){
				//do something
			}
		}
		catch(Exception e)
		{
			
		}
	} 

	public void surfaceDestroyed(SurfaceHolder holder) { 
		mCamera.stopPreview();
		mCamera.release();
		mPreviewRunning = false;
		mCamera = null; 
	} 

	public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {

		if (mPreviewRunning) {
			mCamera.stopPreview();
		}

		Camera.Parameters parameters = mCamera.getParameters();  
		mCamera.setParameters(parameters);
		mCamera.startPreview();
	} 

}