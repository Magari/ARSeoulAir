package com.ARSeoulAir;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ARSeoulAir extends Activity {
    /** Called when the activity is first created. */
	
	public static ARSeoulAir main;
	public static SeoulAir air = new SeoulAir();
	public static Context m_context;
	 
	private Button m_cityBtn;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        main = this;
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        Preview mPreview = new Preview(this); 
        DrawOnTop mDraw = new DrawOnTop(this); 
        
        setContentView(mPreview); 
        addContentView(mDraw, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        m_context = getApplicationContext();
        
        
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        
        m_cityBtn = new Button(this);
        m_cityBtn.setText("도시선택");
        m_cityBtn.setWidth(150);
        m_cityBtn.setHeight(50);
        addContentView(m_cityBtn, p);
        
        m_cityBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ARSeoulAir.this, ActivityCity.class);
				startActivity(intent);
			}
		});
        
        
        
        
        DustDraw.init();
        air.setLocation(SeoulAir.Location.Gangnam);
        //make();
        
        Intent intent = new Intent(ARSeoulAir.this, ActivityCity.class);
		startActivity(intent);
    }
    public void make()
    {
    	DustDraw.deleteAll();
    	air.make();
    	
    	float fO3 = air.getNO2();
    	float fSO2 = air.getSO2();
    	float fNO2 = air.getNO2();
    	float fCO = air.getCO();
    	float fPM10 = air.getPM10();
    	
    	int nO3 = 0;
    	int nSO2= 0;
    	int nNO2= 0;
    	int nCO= 0;
    	int nPM10= 0;
    	
    	// 오존
    	if(fO3 < 0.04f)			nO3 = 1;    			
    	else if(fO3 < 0.08f)	nO3 = 2;
    	else if(fO3 < 0.12f)	nO3 = 3;
    	else if(fO3 < 0.3f)		nO3 = 5;
    	else if(fO3 < 0.5f)		nO3 = 7;
    	else					nO3 = 10;		
    	
    	// 아황산가스
    	if(fSO2 < 0.02f)		nSO2 = 1;    			
    	else if(fSO2 < 0.05f)	nSO2 = 2;
    	else if(fSO2 < 0.1f)	nSO2 = 3;
    	else if(fSO2 < 0.15f)	nSO2 = 4;
    	else if(fSO2 < 0.4f)	nSO2 = 7;
    	else					nSO2 = 10;
    	
    	// 이산화질소
    	if(fNO2 < 0.03f)		nNO2 = 1;    			
    	else if(fNO2 < 0.06f)	nNO2 = 2;
    	else if(fNO2 < 0.15f)	nNO2 = 3;
    	else if(fNO2 < 0.2f)	nNO2 = 4;
    	else if(fNO2 < 0.6f)	nNO2 = 7;
    	else					nNO2 = 10;
    	
    	// 일산화탄소
    	if(fCO < 2f)			nCO = 1;    			
    	else if(fCO < 9f)		nCO = 2;
    	else if(fCO < 12f)		nCO = 3;
    	else if(fCO < 15f)		nCO = 4;
    	else if(fCO < 30f)		nCO = 7;
    	else					nCO = 10;
    	
    	// 미세먼지
    	if(fPM10 < 30f)			nPM10 = 1;    			
    	else if(fPM10 < 80f)	nPM10 = 2;
    	else if(fPM10 < 120f)	nPM10 = 3;
    	else if(fPM10 < 200f)	nPM10 = 4;
    	else if(fPM10 < 300f)	nPM10 = 7;
    	else					nPM10 = 10;
    	
    	
    	DustDraw.make(DustType.SO2, nSO2);
    	DustDraw.make(DustType.CO, nCO);
        DustDraw.make(DustType.NO2, nNO2);
        DustDraw.make(DustType.O3, nO3);
        DustDraw.make(DustType.PM10, nPM10);
        
        
    }
}