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
				break;
		}
	}
}
