package com.example.damiancaloriecount;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddToDiaryScreen extends Activity implements OnClickListener{

	TextView tvProductName, tvCarbs, tvProtein, tvFat, tvKcal, tvCarbsLeft, tvProteinLeft, tvFatLeft, tvKcalLeft;
	Button bAddToDiary, bPlus, bMinus;
	EditText etGrams;
	ListView listView;
	CRUDdb database;
	ArrayList arrayList;
	String s_carbsProduct, s_proteinProduct, s_fatProduct, s_kcalProduct;
	Float f_carbsProduct, f_proteinProduct, f_fatProduct, f_kcalProduct;
	Float carbs,protein,fat,kcal;
	Float carbsLeft, proteinLeft, fatLeft;
	Integer multipler = 1;   //100/100
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_to_diary_screen);
		
		tvProductName = (TextView)findViewById(R.id.tvProductName);
		tvCarbs= (TextView)findViewById(R.id.tvCarbs);
		tvProtein = (TextView)findViewById(R.id.tvProtein);
		tvFat = (TextView)findViewById(R.id.tvFat);
		tvKcal = (TextView)findViewById(R.id.tvKcal);
		tvCarbsLeft = (TextView)findViewById(R.id.tvCarbsLeft);
		tvProteinLeft = (TextView)findViewById(R.id.tvProteinLeft);
		tvFatLeft = (TextView)findViewById(R.id.tvFatLeft);
		tvKcalLeft = (TextView)findViewById(R.id.tvKcalLeft);
		
		bAddToDiary = (Button)findViewById(R.id.bAddToDiary);
		bPlus = (Button)findViewById(R.id.bPlus);
		bMinus = (Button)findViewById(R.id.bMinus);
		
		etGrams = (EditText)findViewById(R.id.etGrams);
		
		
		//get info from intent
		Intent i = getIntent();
		String p_name = i.getExtras().getString("p_name");
		
		//read from database
		database = new CRUDdb(this);
		database.open();
		arrayList = database.getDataAboutProduct(p_name); //0-Name 1-Carbs 2-Protein 3-Fat 4-Kcal
		
		s_carbsProduct = arrayList.get(1).toString();
		f_carbsProduct = Float.valueOf(s_carbsProduct);
		
		s_proteinProduct = arrayList.get(2).toString();
		f_proteinProduct = Float.valueOf(s_proteinProduct);
		
		s_fatProduct = arrayList.get(3).toString();
		f_fatProduct = Float.valueOf(s_fatProduct);
		
	
		/*
		tv.setText(p_name + " carbs: " + s_carbs);
		*/
		bAddToDiary.setOnClickListener(this);
		bPlus.setOnClickListener(this);
		bMinus.setOnClickListener(this);
		
		etGrams.addTextChangedListener(new TextWatcher() {
			
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
				Toast.makeText(getApplicationContext(), s, 0).show();
			}
		});
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

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.bAddToDiary:
				
				break;
			case R.id.bPlus:  
				String s_value = etGrams.getText().toString(); //check !null
				Integer i_value = Integer.valueOf(s_value)+1;
				etGrams.setText(String.valueOf(i_value));
				break;
			case R.id.bMinus:
				String s_value2 = etGrams.getText().toString(); //check !null
				Integer i_value2 = Integer.valueOf(s_value2)-1;
				etGrams.setText(String.valueOf(i_value2));
				
				break;
			}
		}

}
