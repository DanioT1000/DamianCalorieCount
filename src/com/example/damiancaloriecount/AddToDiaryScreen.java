package com.example.damiancaloriecount;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
	float f_carbsProduct, f_proteinProduct, f_fatProduct, f_kcalProduct;
	float carbs,protein,fat,kcal;
	float carbsLeft, proteinLeft, fatLeft;
	int grams = 100;   
	String p_name;
	
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
		p_name = i.getExtras().getString("p_name");
		
		//read from database
		database = new CRUDdb(this);
		database.open();
		arrayList = database.getDataAboutProduct(p_name); //0-Name 1-Carbs 2-Protein 3-Fat 4-Kcal
		
		tvProductName.setText(arrayList.get(0).toString());
		
		s_carbsProduct = arrayList.get(1).toString();
		f_carbsProduct = Float.valueOf(s_carbsProduct);
		
		s_proteinProduct = arrayList.get(2).toString();
		f_proteinProduct = Float.valueOf(s_proteinProduct);
		
		s_fatProduct = arrayList.get(3).toString();
		f_fatProduct = Float.valueOf(s_fatProduct);
		
		s_kcalProduct = arrayList.get(4).toString();
		f_kcalProduct = Float.valueOf(s_kcalProduct);
		
		carbs=f_carbsProduct;
		protein= f_proteinProduct;
		fat = f_fatProduct;
		kcal = f_carbsProduct;
		
		
		
		
		updatTextViews(f_carbsProduct,f_proteinProduct,f_fatProduct,f_kcalProduct,160,170,180,190);
	
		//*OnClickListener
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
				if(!s.toString().equals("")){
					//Toast.makeText(getApplicationContext(), s, 0).show();
					//String s_value = String.valueOf(s);					
					int i_value = Integer.valueOf(s.toString());
					carbs = ((float)i_value/100)*f_carbsProduct;
					protein = ((float)i_value/100)*f_proteinProduct;
					fat = ((float)i_value/100)*f_fatProduct;
					kcal = (4*carbs) + (4* protein) + (9* fat);				
				} else{
					carbs = 0;
					protein = 0;
					fat = 0;
					kcal = 0;
				}
				updatTextViews(carbs, protein, fat, kcal, 0,0,0,0);
				
			}
		});
		//****end OnClickListener
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
	    
    public void updatTextViews(float carbs, float protein, float fat, float kcal, float carbsleft, float proteinleft, float fatleft, float kcalleft){
    	tvCarbs.setText(String.valueOf(carbs));
    	tvProtein.setText(String.valueOf(protein));
    	tvFat.setText(String.valueOf(fat));
    	tvKcal.setText(String.valueOf(kcal));
    	
    	tvCarbsLeft.setText(String.valueOf(carbsleft));
    	tvProteinLeft.setText(String.valueOf(proteinleft));
    	tvFatLeft.setText(String.valueOf(fatleft));
    	tvKcalLeft.setText(String.valueOf(kcalleft));
    }
	    

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bAddToDiary:
			database.addToDiary(p_name, carbs, protein, fat, kcal);
			Toast.makeText(getApplicationContext(), "Added To database", 0).show();
			Intent i = new Intent(this, MainScreen.class);
			startActivity(i);
			break;
		case R.id.bPlus:  
			String s_value = etGrams.getText().toString(); //check !null
			if(!s_value.equals("")){
				int i_value = Integer.valueOf(s_value);
				int incValue = i_value+1;
				etGrams.setText(String.valueOf(incValue));
				carbs = ((float)incValue/100)*f_carbsProduct;
				protein = ((float)incValue/100)*f_proteinProduct;
				fat = ((float)incValue/100)*f_fatProduct;
				kcal = (4*carbs) + (4* protein) + (9* fat);
			}
			updatTextViews(carbs, protein, fat, kcal, 0,0,0,0);
			break;
		case R.id.bMinus:
			String s_value2 = etGrams.getText().toString(); //check !null
			if(!s_value2.equals("")){
				int i_value2 = Integer.valueOf(s_value2);
				int decValue2 = i_value2-1;
				if(decValue2 >0){
					etGrams.setText(String.valueOf(decValue2));
					carbs = ((float)decValue2/100)*f_carbsProduct;
					protein = ((float)decValue2/100)*f_proteinProduct;
					fat = ((float)decValue2/100)*f_fatProduct;
					kcal = (4*carbs) + (4* protein) + (9* fat);
				} else {
					etGrams.setText("0");
					carbs = 0;
					protein = 0;
					fat = 0;
					kcal = 0;
				}
			}
			updatTextViews(carbs, protein, fat, kcal, 0,0,0,0);
			
			break;
		}
	}

}
