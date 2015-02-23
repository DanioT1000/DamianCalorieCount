package com.example.damiancaloriecount;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends Activity implements OnClickListener {

	TextView tvDate;
	TextView tvCaloriesLeft;
	TextView tvCarbsLeft;
	TextView tvProteinLeft;
	TextView tvFatLeft;
	TextView tvTotalCalories;
	Button bAddToDiary;
	Button bShowTodaysProducts;
	Button bAddProductToDatabase;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvCaloriesLeft = (TextView)findViewById(R.id.tvCaloriesLeft);
        tvCarbsLeft = (TextView) findViewById(R.id.tvCarbsLeft);
        tvProteinLeft = (TextView) findViewById(R.id.tvProteinLeft);
        tvFatLeft = (TextView) findViewById(R.id.tvFatLeft);
        tvTotalCalories = (TextView) findViewById(R.id.tvTotalCalories);
        
        bAddToDiary = (Button)findViewById(R.id.bAddToDiary);
        bShowTodaysProducts = (Button)findViewById(R.id.bShowTodaysProducts);
        bAddProductToDatabase = (Button)findViewById(R.id.bAddProductToDatabase);
        
        
        bAddToDiary.setOnClickListener(this);
        bShowTodaysProducts.setOnClickListener(this);
        bAddProductToDatabase.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.bAddToDiary:
				Intent i = new Intent("com.example.damiancaloriecount.SEARCHSCREEN");
				startActivity(i);
				break;	
			case R.id.bShowTodaysProducts:
				Intent i2 = new Intent("com.example.damiancaloriecount.LISTTODAYSPRODUCTSCREEN");
				startActivity(i2);
				
				/*
				CRUDdb entry = new CRUDdb(MainScreen.this);
				entry.open();
				
				entry.createEntry("ziemniak", (float)1, (float)2, (float)3, (float)4);
				entry.createEntry("majonez", (float)1, (float)2, (float)3, (float)4);
				entry.createEntry("chleb", (float)1, (float)2, (float)3, (float)4);
				
				entry.close();
				*/
				break;
			case R.id.bAddProductToDatabase:
				Intent i3 = new Intent("com.example.damiancaloriecount.ADDTOPRODUCTDATABASE");
				startActivity(i3);
				break;
		}
	}
}
