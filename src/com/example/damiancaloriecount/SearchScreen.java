package com.example.damiancaloriecount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchScreen extends Activity implements OnClickListener {

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	//String[] name = {"cos", "cosik", "cosik2", "cosik3", "agata", "barbara", "dupa"};
	AutoCompleteTextView autoTextView;
	Button bSearch;
	ListView listView;
	CRUDdb info;
	ArrayAdapter<String> adapter2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_screen);

		autoTextView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
		bSearch = (Button)findViewById(R.id.bSearch);
		listView = (ListView)findViewById(R.id.listView);
		
		info = new CRUDdb(this);
		info.open();
		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, name);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,info.getDataArrayList() );
		
		
		autoTextView.setThreshold(2);
		autoTextView.setAdapter(adapter);
		
		
		bSearch.setOnClickListener(this);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Object o = parent.getItemAtPosition(position);
	            String keyword = o.toString();
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Parent: "+parent+"" + "postion: "+position+" id: "+ id+" text: "+ keyword, 0).show();
				Intent i = new Intent(SearchScreen.this, AddToDiaryScreen.class);
				i.putExtra("p_name", keyword);
				startActivity(i);
			}
		});
		
		

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bSearch:
			String productName = autoTextView.getText().toString();
			ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,info.searchDataAndReturnArrayList(productName) );
			listView.setAdapter(adapter2);
			break;
		}
	}
	
	  @Override
	    public void onResume() {
	        super.onResume();
	        info.open();
	    }

	    @Override
	    public void onPause() {
	        super.onPause();
	        info.close();
	    }

}
