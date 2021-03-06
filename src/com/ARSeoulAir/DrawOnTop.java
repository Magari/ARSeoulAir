package com.ARSeoulAir;

import android.content.Context;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;



class DrawOnTop extends View {

	public static int m_nWidth = 0;
	public static int m_nHeight = 0;

	public DrawOnTop(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		Display defaultDisplay = ((WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

		m_nWidth = defaultDisplay.getWidth();
		m_nHeight = defaultDisplay.getHeight();

		
		new CountDownTimer(Integer.MAX_VALUE, 20) {
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				invalidate();
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				
				this.start();
			}
		}.start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub

		DustDraw.update();
		DustDraw.draw(canvas);

		super.onDraw(canvas);
	}

}