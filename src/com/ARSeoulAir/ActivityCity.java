package com.ARSeoulAir;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityCity extends Activity implements AdapterView.OnItemClickListener
{
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city);
   
        ArrayList<String> arGeneral = new ArrayList<String>();
        
        arGeneral.add("404, ������");
        arGeneral.add("405, ������");
        arGeneral.add("202, ���ϱ�");
        arGeneral.add("308, ������");
        arGeneral.add("303, ���Ǳ�");
        arGeneral.add("201, ������");
        arGeneral.add("301, ���α�");
        arGeneral.add("305, ��õ��");
        arGeneral.add("206, �����");
        arGeneral.add("208, ������");
        arGeneral.add("203, ���빮��");
        arGeneral.add("305, ���۱�");
		arGeneral.add("105, ������");
		arGeneral.add("101, ���빮��");
		arGeneral.add("401, ���ʱ�");
		arGeneral.add("207, ������");
		arGeneral.add("204, ���ϱ�");
		arGeneral.add("402, ���ı�");
		arGeneral.add("302, ��õ��");
		arGeneral.add("304, ��������");
		arGeneral.add("102, ��걸");
		arGeneral.add("106, ����");
		arGeneral.add("103, ���α�");
		arGeneral.add("104, �߱�");
        
       
        ArrayAdapter<String> Adapter;
        Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arGeneral);
 
        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(Adapter);
        
        list.setOnItemClickListener(this);
    }
    	
    	
	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
		{
			ARSeoulAir.main.make();
			//return true;
		}
		return super.onKeyDown(keyCode, event);
    }


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		String strItemText=((TextView)arg1).getText().toString();
		String strCityID = "";
		int nIndex = strItemText.indexOf(",");
		if(0 <= nIndex)
		{
			strCityID = strItemText.substring(0, nIndex);
		}
		
		ARSeoulAir.air.setLocation(strCityID);
		ARSeoulAir.main.make();
		finish();
	}

}
