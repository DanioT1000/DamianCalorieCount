package com.example.damiancaloriecount;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.AdapterViewCompat.OnItemClickListener;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchScreen extends Activity {

	EditText etSearch;
	ListView listView;
	CRUDdb info;
	ArrayAdapter<String> adapter = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_screen);
		
		//xml elements
		etSearch = (EditText) findViewById(R.id.etSearch);
		listView = (ListView)findViewById(R.id.listView);
		
		//database
		info = new CRUDdb(this);
		info.open();
		
		//set names to adapter in asynctask
		new DownloadProductNames().execute();
			
		//actions
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Object o = parent.getItemAtPosition(position);
	            String keyword = o.toString();
				// TODO Auto-generated method stub
				Intent i = new Intent(SearchScreen.this, AddToDiaryScreen.class);
				i.putExtra("p_name", keyword);
				startActivity(i);
			}
		});
		
		etSearch.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				//filter
				String text = etSearch.getText().toString();
				adapter.getFilter().filter(text);
				
			}
		});
		
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
	    
	    
	private class DownloadProductNames extends AsyncTask<Void, Void, Void>{
		
		@Override
		protected Void doInBackground(Void... params) {			
			//data form db
			adapter = new ArrayAdapter<String>(SearchScreen.this, android.R.layout.simple_dropdown_item_1line,info.getDataArrayList() );			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void args) {
			listView.setAdapter(adapter);
		}
		
	} //end class

}



