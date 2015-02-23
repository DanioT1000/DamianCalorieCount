package com.example.damiancaloriecount;

import android.app.Activity;
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
				String name = etName.getText().toString();
				float carbs = Float.valueOf(etCarbs.getText().toString());
				float protein = Float.valueOf(etProtein.getText().toString());
				float fat = Float.valueOf(etFat.getText().toString());
				float kcal = (4*carbs) + (4*protein) + (9*fat);
				Toast.makeText(getApplicationContext(), name + String.valueOf(carbs), 0).show();
				long cos = database.createEntry(name, carbs, protein, fat, kcal); //return id
				Toast.makeText(getApplicationContext(), String.valueOf(cos), 0).show(); //show id
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
