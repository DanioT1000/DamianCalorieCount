package com.example.damiancaloriecount;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class AddToDiaryScreen extends Activity {

	TextView tv;
	ListView listView;
	CRUDdb database;
	ArrayList arrayList;
	String s_carbs;
	Float f_carbs;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_to_diary_screen);
		
		tv=(TextView)findViewById(R.id.textView1);
		Intent i = getIntent();
		String p_name = i.getExtras().getString("p_name");
		
		database = new CRUDdb(this);
		database.open();
		arrayList = database.getDataAboutProduct(p_name); //0-Name 1-Carbs 2-Protein 3-Fat 4-Kcal
		
		s_carbs = arrayList.get(3).toString();
		f_carbs = Float.valueOf(s_carbs);
		
		tv.setText(p_name + " carbs: " + s_carbs);
	}
	
	  @Override
	    public void onResume() {
	        super.onResume();
	        database.open();
	    }

	    @Override
	    public void onPause() {
	        super.onPause();
	        database.close();
	    }

}
