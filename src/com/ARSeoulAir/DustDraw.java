package com.ARSeoulAir;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.WindowManager;


enum DustType
{
	O3("5"),
	SO2("8"),
	NO2("15"),
	CO("25"),
	PM10("40");
	
	private String value;
	DustType(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return this.value;
	}
	
}
class Dust //부모클래스
{	
	protected final int DEFAULT_SIZE = 5;
	protected final int VELOCITY_LIMIT = 3;
	protected final int ROTATE_LIMIT = 3;
	
	protected Random m_random = new Random();
	
	protected Vector2 m_vPos = new Vector2(0,0);
	protected Vector2 m_vVelocity = new Vector2(0,0);
	//protected Vector2 m_vSize;
	
	protected float m_fSpeed;
	
	protected int m_nAngle;
	protected int m_nRotateSpeed;
	 
	public Dust()
	{
		//m_vSize.vec[0] = DEFAULT_SIZE;
		//m_vSize.vec[1] = DEFAULT_SIZE;
		
		m_vPos.vec[0] = m_random.nextInt(DrawOnTop.m_nWidth)-DEFAULT_SIZE*2;
		m_vPos.vec[1] = m_random.nextInt(DrawOnTop.m_nHeight)-DEFAULT_SIZE*2;
		
		m_vVelocity.vec[0] = m_random.nextFloat()*2-1;
		m_vVelocity.vec[1] = m_random.nextFloat()*2-1;
		
		m_nAngle = m_random.nextInt(360);
		
		m_fSpeed = m_random.nextFloat()*VELOCITY_LIMIT+1;
		m_nRotateSpeed = m_random.nextInt(ROTATE_LIMIT)+1;
		
	}
	
	public void update()
	{
		m_nAngle += m_nRotateSpeed;
		
		m_vVelocity.normalize();
		m_vVelocity.multiply(m_fSpeed);
		m_vPos.add(m_vVelocity);
		
		// block check
		// 그냥 크기 생각 안함
		if(m_vPos.vec[0] < 0 || DrawOnTop.m_nWidth < m_vPos.vec[0])
		{
			m_vVelocity.vec[0] *= -1;
		}
		if(m_vPos.vec[1] < 0 || DrawOnTop.m_nHeight < m_vPos.vec[1])
		{
			m_vVelocity.vec[1] *= -1;
		}	
	}
	public void draw(Canvas canvas)
	{
		
	}
}

class DustOzone extends Dust //오존
{
	
	public void draw(Canvas canvas)
	{
		Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL); 
        paint.setStrokeWidth(10);
        
        
        double fRad = Math.toRadians(m_nAngle);
        Vector2 vOther = new Vector2((float)Math.cos(fRad), (float)Math.sin(fRad));
        vOther.normalize();
        vOther.multiply(10.0f);
        vOther.add(m_vPos);
        
        paint.setColor(Color.BLUE);
        canvas.drawCircle(m_vPos.vec[0], m_vPos.vec[1], DEFAULT_SIZE, paint);
        
        paint.setColor(Color.CYAN);
        canvas.drawCircle(vOther.vec[0], vOther.vec[1], DEFAULT_SIZE, paint);
	}
}

class DustSo2 extends Dust //아황산가스
{
	public void draw(Canvas canvas)
	{
		Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL); 
        paint.setStrokeWidth(10);
        
        
        double fRad = Math.toRadians(m_nAngle);
        Vector2 vOther = new Vector2((float)Math.cos(fRad), (float)Math.sin(fRad));
        Vector2 vOther2 = new Vector2(0,0);
        
        vOther.normalize();
        vOther2.set(vOther);
        vOther.multiply(10.0f);
        vOther2.multiply(-10.0f);
        vOther.add(m_vPos);
        vOther2.add(m_vPos);
        
        
        
        paint.setColor(Color.MAGENTA);
        canvas.drawCircle(vOther.vec[0], vOther.vec[1], DEFAULT_SIZE, paint);
        canvas.drawCircle(vOther2.vec[0], vOther2.vec[1], DEFAULT_SIZE, paint);
        
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(m_vPos.vec[0], m_vPos.vec[1], DEFAULT_SIZE, paint);
	}
}

class DustNo2 extends Dust //이산화질소
{
	public void draw(Canvas canvas)
	{
		Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL); 
        paint.setStrokeWidth(1);
        
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(m_vPos.vec[0], m_vPos.vec[1], DEFAULT_SIZE, paint);
        
        paint.setColor(Color.GREEN);
        canvas.drawRect(m_vPos.vec[0] - DEFAULT_SIZE/2, m_vPos.vec[1] - DEFAULT_SIZE/2,
        		m_vPos.vec[0] + DEFAULT_SIZE/2, m_vPos.vec[1] + DEFAULT_SIZE/2, paint);
	}
}

class DustCo extends Dust //일산화탄소
{
	public void draw(Canvas canvas)
	{
		Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL); 
        paint.setStrokeWidth(1);
        paint.setColor(Color.RED);
        
        canvas.drawCircle(m_vPos.vec[0], m_vPos.vec[1], DEFAULT_SIZE, paint);
	}
}

class DustPm10 extends Dust //미세먼지
{
	public void draw(Canvas canvas)
	{
		Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL); 
        paint.setStrokeWidth(2);
        paint.setColor(Color.GRAY);
        
        double fRad = Math.toRadians(m_nAngle);
        for(int i=0; i<=360; i+=30)
        {
        	Vector2 vOther = new Vector2((float)Math.cos(i+fRad), (float)Math.sin(i+fRad));
            vOther.normalize();
            vOther.multiply(15.0f);
            vOther.add(m_vPos);
            
            canvas.drawLine(m_vPos.vec[0], m_vPos.vec[1], vOther.vec[0], vOther.vec[1], paint);
        }
        
        paint.setStrokeWidth(3);
        canvas.drawCircle(m_vPos.vec[0], m_vPos.vec[1], DEFAULT_SIZE/2, paint);
	}
}


public class DustDraw 
{	
	private static ArrayList<Dust> m_list = new ArrayList<Dust>();
	
	public static void init()
	{
		
	}
	public static void draw(Canvas canvas)
	{
		for(int i=0; i<m_list.size(); i++)
		{
			m_list.get(i).draw(canvas);
		}
		
		
		int nStartX = DrawOnTop.m_nWidth-140;
		int nStartY = 90;
		
		Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL); 
        paint.setStrokeWidth(2);
        paint.setColor(Color.WHITE);
		canvas.drawRect(nStartX+0, 0, nStartX+130, 240, paint);
		
		Resources res = ARSeoulAir.m_context.getResources();
		Bitmap image = null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
		if (ARSeoulAir.air.getLevel() == 1)
			image = BitmapFactory.decodeResource(res, R.drawable.cha01);
		else if (ARSeoulAir.air.getLevel() == 2)
			image = BitmapFactory.decodeResource(res, R.drawable.cha02);
		else if (ARSeoulAir.air.getLevel() == 3)
			image = BitmapFactory.decodeResource(res, R.drawable.cha03);
		else if (ARSeoulAir.air.getLevel() == 4)
			image = BitmapFactory.decodeResource(res, R.drawable.cha04);
		else if (ARSeoulAir.air.getLevel() == 5)
			image = BitmapFactory.decodeResource(res, R.drawable.cha05);
        
		if(image != null)
		{
			canvas.drawBitmap(image, nStartX+30, 5, null);
		}
		
		Bitmap imageinfo = null;
		imageinfo = BitmapFactory.decodeResource(res, R.drawable.info);
		if(imageinfo != null)
		{
			canvas.drawBitmap(imageinfo, nStartX-45, 240, null);
		}
		
		paint.setColor(Color.BLACK);
		
		
		String strState = "";
		if(ARSeoulAir.air.getLevel() == 1) strState = "좋음";
		else if(ARSeoulAir.air.getLevel() == 2) strState = "보통";
		else if(ARSeoulAir.air.getLevel() == 3) strState = "민감군영향";
		else if(ARSeoulAir.air.getLevel() == 4) strState = "나쁨";
		else if(ARSeoulAir.air.getLevel() == 5) strState = "매우나쁨";
		else 									strState = "위험";
		
		canvas.drawText("상태  : " + strState, 		   	nStartX+10, nStartY + 0, 	paint);
		canvas.drawText("위치  : " + ARSeoulAir.air.getLocation().toDong(), 	nStartX+10, nStartY + 15*1, 	paint);
		canvas.drawText("온도 : " + ARSeoulAir.air.getTemperature() + "degC", nStartX+10, nStartY + 15*2, 	paint);
		canvas.drawText("습도 : " + ARSeoulAir.air.getHumidity() + "%RH", 	nStartX+10, nStartY + 15*3, 	paint);
		canvas.drawText("풍속 : " + ARSeoulAir.air.getWindSpeed() + "m.sec",	nStartX+10, nStartY + 15*4, 	paint);
		
		canvas.drawText("아황산가스 : " + ARSeoulAir.air.getSO2() + "ppm", 	nStartX+10, nStartY + 15*5, 	paint);
		canvas.drawText("오존 : " +  ARSeoulAir.air.getO3() + "ppm", 	nStartX+10, nStartY + 15*6, 	paint);
		canvas.drawText("이산화질소 : " + ARSeoulAir.air.getNO2() + "ppm", 	nStartX+10, nStartY + 15*7, 	paint);
		canvas.drawText("일산화탄소 : " + ARSeoulAir.air.getCO() + "ppm", 	nStartX+10, nStartY + 15*8, 	paint);
		canvas.drawText("미세먼지 : " + ARSeoulAir.air.getPM10() + "㎍/㎥", 	nStartX+10, nStartY + 15*9, 	paint);
		
		
		
		
	}
	public static void update()
	{
		for(int i=0; i<m_list.size(); i++)
		{
			m_list.get(i).update();
		}
	}
	public static void dispose()
	{
		deleteAll();
	}
	
	public static void make(DustType type, int nCount)
	{
		for(int i=0; i<nCount; i++)
		{
			Dust dust = null;
			
			if(type == DustType.CO)
			{	
				dust = new DustCo();
			}
			else if(type == DustType.NO2)
			{
				dust = new DustNo2();
			}
			else if(type == DustType.O3)
			{
				dust = new DustOzone();
			}
			else if(type == DustType.PM10)
			{
				dust = new DustPm10();
			}
			else if(type == DustType.SO2)
			{
				dust = new DustSo2();
			}
			
			if(dust != null)
			{
				m_list.add(dust);
			}
			
		}
	}
	public static void deleteAll()
	{
		for(int i=0; i<m_list.size(); i++)
		{
			m_list.remove(i);
		}
	}
	
}
