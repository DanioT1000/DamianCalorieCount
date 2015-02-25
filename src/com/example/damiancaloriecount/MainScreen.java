package com.example.damiancaloriecount;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	CRUDdb database;
	float carbs, protein, fat;
	float carbsLeft, proteinLeft, fatLeft;
	Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE yyyy-MM-dd");
	
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
        //open database
        database = new CRUDdb(this);
        database.open();
        //updateTV
        updateTv();
       
        
    }
    
    public void updateTv(){
    	 //get info from database
    	  carbs = database.getTodaysCarbs();
          protein = database.getTodaysProtein();
          fat = database.getTodaysFat();
          
          tvTotalCalories.setText(String.valueOf((4*carbs) + (4*protein) + (9 *fat)));
          
          carbsLeft = 404 - carbs;
          proteinLeft = 173 - protein;
          fatLeft = 80 - fat;        
          
          tvCaloriesLeft.setText(String.valueOf((4*carbsLeft) + (4*proteinLeft) + (9 *fatLeft)));
          
          tvCarbsLeft.setText(String.valueOf(carbsLeft));
          tvProteinLeft.setText(String.valueOf(proteinLeft));
          tvFatLeft.setText(String.valueOf(fatLeft));
          
          //update date        
          String strDate = sdf.format(c.getTime());
          tvDate.setText(strDate);
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
				break;
			case R.id.bAddProductToDatabase:
				Intent i3 = new Intent("com.example.damiancaloriecount.ADDTOPRODUCTDATABASE");
				startActivity(i3);
				break;
		}
	}
	
	  @Override
	    public void onResume() {
	        super.onResume();
	        database.open();
	        updateTv();
	    }

	    @Override
	    public void onPause() {
	        super.onPause();
	        database.close();
	    }
}
