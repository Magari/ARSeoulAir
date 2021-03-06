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
        
        arGeneral.add("404, 강남구");
        arGeneral.add("405, 강동구");
        arGeneral.add("202, 강북구");
        arGeneral.add("308, 강서구");
        arGeneral.add("303, 관악구");
        arGeneral.add("201, 광진구");
        arGeneral.add("301, 구로구");
        arGeneral.add("305, 금천구");
        arGeneral.add("206, 노원구");
        arGeneral.add("208, 도봉구");
        arGeneral.add("203, 동대문구");
        arGeneral.add("305, 동작구");
		arGeneral.add("105, 마포구");
		arGeneral.add("101, 서대문구");
		arGeneral.add("401, 서초구");
		arGeneral.add("207, 성동구");
		arGeneral.add("204, 성북구");
		arGeneral.add("402, 송파구");
		arGeneral.add("302, 양천구");
		arGeneral.add("304, 영등포구");
		arGeneral.add("102, 용산구");
		arGeneral.add("106, 은평구");
		arGeneral.add("103, 종로구");
		arGeneral.add("104, 중구");
        
       
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
