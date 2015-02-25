package com.example.damiancaloriecount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddToProductDatabase extends Activity implements OnClickListener {

	EditText etName, etCarbs, etProtein, etFat;
	Button bAddToProductDatabase;
	CRUDdb database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_to_product_database_screen);
		
		etName = (EditText)findViewById(R.id.etName);
		etCarbs = (EditText)findViewById(R.id.etCarbs);
		etProtein = (EditText)findViewById(R.id.etProtein);
		etFat = (EditText)findViewById(R.id.etFat);
		
		bAddToProductDatabase = (Button)findViewById(R.id.bAddToProductDatabase);
		
		database = new CRUDdb(this);
		database.open();
		
		bAddToProductDatabase.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.bAddToProductDatabase:
				float carbs,protein, fat, kcal;
				String name = etName.getText().toString();
				String s_carbs = etCarbs.getText().toString();
				String s_protein = etProtein.getText().toString();
				String s_fat = etFat.getText().toString();
				if(!name.equals("") && !s_carbs.equals("") && !s_protein.equals("") && !s_fat.equals("")){
					carbs = Float.valueOf(s_carbs);
					protein = Float.valueOf(s_protein);
					fat = Float.valueOf(s_fat);
					kcal = (4*carbs) + (4*protein) + (9*fat);
					//add to database
					long cos = database.createEntry(name, carbs, protein, fat, kcal); //return id
					Toast.makeText(getApplicationContext(), String.valueOf(cos), 0).show(); //show id
					Intent i = new Intent(this, MainScreen.class);
					startActivity(i);
				} else 
					Toast.makeText(getApplicationContext(), "Fields can not be empty", 0).show();
				
				break;
		}
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
