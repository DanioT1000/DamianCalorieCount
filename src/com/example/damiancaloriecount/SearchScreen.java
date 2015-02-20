package com.example.damiancaloriecount;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchScreen extends Activity {

	TextView tvSearchResults;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_screen);
		
		tvSearchResults = (TextView)findViewById(R.id.textView1);
		
		CRUDdb info = new CRUDdb(this);
		info.open();
		String data = info.getData();
		info.close();
		tvSearchResults.setText(data);
	}

}
