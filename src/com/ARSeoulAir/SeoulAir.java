package com.ARSeoulAir;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class SeoulAir 
{
	public enum Location
	{
		Gangnam("404", "������"),
		Gangdong("405", "������"),
		Gangbuk("202", "���ϱ�"),
		Gangseo("308", "������"),
		Gwanak("303", "���Ǳ�"),
		Gwangjin("201", "������"),
		Guro("301", "���α�") ,
		Geumcheon("305", "��õ��"),
		Nowon("206", "�����"),
		Dobong("208", "������"),
		Dongdaemun("203", "���빮��"),
		Dongjak("305", "���۱�"),
		Mapo("105", "������"),
		Seodaemun("101", "���빮��"),
		Seocho("401", "���ʱ�"),
		Seongdong("207", "������"),
		Seongbuk("204", "���ϱ�"),
		Songpa("402", "���ı�"),
		Yangcheon("302", "��õ��"),
		Yeongdeungpo("304", "��������"),
		Yongsan("102", "��걸"),
		Eunpyeong("106", "����"),
		Jongno("103", "���α�"),
		Jung("104", "�߱�"),
		Jungnang("205", "�߶���");
		
		private String value;
		private String value2;
		
		Location(String value, String value2)
		{
			this.value = value;
			this.value2 = value2;
		}
		
		@Override
		public String toString()
		{
			return this.value;
		}
		public String toDong()
		{
			return this.value2;
		}
	}
	
	private final String m_strBase = "http://air.seoul.go.kr/jsp/airpollution_total.jsp?cmd=total&itemCode=106&";
	private final String m_strTail = "&itemName=null&siteName=null";
	
	
	
	private float m_fTemperature = 0.0f;
	private float m_fHumidity = 0.0f;
	private float m_fWindSpeed = 0.0f;
	
	private float m_fO3 = 0.0f;
	private float m_fSO2 = 0.0f;
	private float m_fNO2 = 0.0f;
	private float m_fCO = 0.0f;
	private float m_fPM10 = 0.0f;
	
	private int m_nLevel = 0;
	
	private Location m_location = Location.Dobong;
	//private String m_location = Location.Dobong.toString();
	
	public int getLevel()
	{
		return m_nLevel;
	}
	
	public float getTemperature()
	{
		return m_fTemperature;
	}
	public float getHumidity()
	{
		return m_fHumidity;
	}
	public float getWindSpeed()
	{
		return m_fWindSpeed;
	}
	
	public float getO3()
	{
		return m_fO3;
	}
	public float getSO2()
	{
		return m_fSO2;
	}
	public float getNO2()
	{
		return m_fNO2;
	}
	public float getCO()
	{
		return m_fCO;
	}
	public float getPM10()
	{
		return m_fPM10;
	}
	
	public void setLocation(Location location)
	{
		m_location = location;
	}
	
	public void setLocation(String strLocation)
	{
		for (Location p : Location.values())
		{
			if(p.toString().compareTo(strLocation) == 0)
			{
				m_location = p;
				break;
			}
		}
	}
	
	public Location getLocation()
	{
		return m_location;
	}
	
	public void make()
	{
		String strResult = "";
		String strURL = "";
		strURL += m_strBase;
		strURL += "siteCode=" + m_location;
		strURL += m_strTail;
		
		try
		{		
			URL url = new URL(strURL);	
			URLConnection connection = url.openConnection();	
			HttpURLConnection hurlc = (HttpURLConnection)connection;	
			hurlc.setRequestMethod("GET");	
			hurlc.setDoOutput(true);	
			hurlc.setDoInput(true);	
			hurlc.setUseCaches(false);	
			hurlc.setDefaultUseCaches(false);
			
			PrintWriter out = new PrintWriter(hurlc.getOutputStream());		
			out.close();	
			
			BufferedReader in = new BufferedReader(new InputStreamReader(hurlc.getInputStream()));		
			String inputLine = null;
			
			while ((inputLine = in.readLine()) != null)
			{
				strResult += inputLine;
			}	
			in.close();		
			
		}
		catch(Exception ex)
		{		
			System.out.println(ex);	
		}	

		StringHelper helper = new StringHelper(strResult);
		
		helper.find("<!--�µ�-->", "", true);
		helper.find("<font color=\"#317DB5\"><strong>", "</strong>", true);
		m_fTemperature = Float.parseFloat(helper.getResult());
		
		helper.find("<!--����-->", "", true);
		helper.find("<font color=\"#29969C\"><strong>", "</strong>", true);
		m_fHumidity = Float.parseFloat(helper.getResult());
		
		helper.find("<!--ǳ��-->", "", true);
		helper.find("<font color=\"#4AA229\"><strong>", "</strong>", true);
		m_fWindSpeed = Float.parseFloat(helper.getResult());
		
		helper.find("��Ȳ�갡��:", "ppm", true);
		m_fSO2 = Float.parseFloat(helper.getResult());
		
		helper.find("����:", "ppm", true);
		m_fO3 = Float.parseFloat(helper.getResult());
		
		helper.find("�̻�ȭ����:", "ppm", true);
		m_fNO2 = Float.parseFloat(helper.getResult());
		
		helper.find("�ϻ�ȭź��:", "ppm", true);
		m_fCO = Float.parseFloat(helper.getResult());
		
		helper.find("�̼�����:", "��/��", true);
		m_fPM10 = Float.parseFloat(helper.getResult());
		
		int nLevel = 0;
		
		// ����
    	if(m_fO3 < 0.04f)		nLevel+= 1;    			
    	else if(m_fO3 < 0.08f)	nLevel+= 2;
    	else if(m_fO3 < 0.12f)	nLevel+= 3;
    	else if(m_fO3 < 0.3f)	nLevel+= 4;
    	else if(m_fO3 < 0.5f)	nLevel+= 5;
    	else					nLevel+= 6;		
    	
    	// ��Ȳ�갡��
    	if(m_fSO2 < 0.02f)		nLevel+= 1;			
    	else if(m_fSO2 < 0.05f)	nLevel+= 2;
    	else if(m_fSO2 < 0.1f)	nLevel+= 3;
    	else if(m_fSO2 < 0.15f)	nLevel+= 4;
    	else if(m_fSO2 < 0.4f)	nLevel+= 5;
    	else					nLevel+= 6;
    	
    	// �̻�ȭ����
    	if(m_fNO2 < 0.03f)		nLevel+= 1;    			
    	else if(m_fNO2 < 0.06f)	nLevel+= 2;
    	else if(m_fNO2 < 0.15f)	nLevel+= 3;
    	else if(m_fNO2 < 0.2f)	nLevel+= 4;
    	else if(m_fNO2 < 0.6f)	nLevel+= 5;
    	else					nLevel+= 6;
    	
    	// �ϻ�ȭź��
    	if(m_fCO < 2f)			nLevel+= 1;    			
    	else if(m_fCO < 9f)		nLevel+= 2;
    	else if(m_fCO < 12f)	nLevel+= 3;
    	else if(m_fCO < 15f)	nLevel+= 4;
    	else if(m_fCO < 30f)	nLevel+= 5;
    	else					nLevel+= 6;
    	
    	// �̼�����
    	if(m_fPM10 < 30f)		nLevel+= 1;    			
    	else if(m_fPM10 < 80f)	nLevel+= 2;
    	else if(m_fPM10 < 120f)	nLevel+= 3;
    	else if(m_fPM10 < 200f)	nLevel+= 4;
    	else if(m_fPM10 < 300f)	nLevel+= 5;
    	else					nLevel+= 6;
    	
    	m_nLevel = nLevel/8;
	}
}
