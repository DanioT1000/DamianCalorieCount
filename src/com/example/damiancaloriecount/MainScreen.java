package com.example.damiancaloriecount;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainScreen extends Activity implements OnClickListener {  //ActionBarActivity

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
	float myCarbs, myProtein, myFat;
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
        
        //show menu item in actionbarc
        getOverflowMenu();

    }
    
    public void updateTv(){
    	 //get info from database
    	  carbs = database.getTodaysCarbs();
          protein = database.getTodaysProtein();
          fat = database.getTodaysFat();
          
          tvTotalCalories.setText(String.valueOf((4*carbs) + (4*protein) + (9 *fat)));
          
          //read preferences       xml/prefs.xml 
          SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
          myCarbs = Float.parseFloat(getPrefs.getString("carbs", "0"));
          myProtein = Float.parseFloat(getPrefs.getString("protein", "0"));
          myFat = Float.parseFloat(getPrefs.getString("fat", "0"));
          
          carbsLeft = myCarbs - carbs;   
          proteinLeft = myProtein - protein;  
          fatLeft = myFat - fat;         
          
          tvCaloriesLeft.setText(String.valueOf((4*carbsLeft) + (4*proteinLeft) + (9 *fatLeft)));
          
          tvCarbsLeft.setText(String.valueOf(carbsLeft));
          tvProteinLeft.setText(String.valueOf(proteinLeft));
          tvFatLeft.setText(String.valueOf(fatLeft));
          
          //update date        
          String strDate = sdf.format(c.getTime());
          tvDate.setText(strDate);
    }
    
    //show menu item in action bar on android 4.1.2
    private void getOverflowMenu() {

    	try {
    	   ViewConfiguration config = ViewConfiguration.get(this);
    	   Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
    	   if(menuKeyField != null) {
    	       menuKeyField.setAccessible(true);
    	       menuKeyField.setBoolean(config, false);
    	   }
    	} catch (Exception e) {
    	   e.printStackTrace();
    	}
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
				Intent i3 = new Intent(this, AddToDBChooseMethod.class);
				startActivity(i3);
				break;
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_menu_screen, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
			case R.id.iAboutAuthor:
				Toast.makeText(getApplicationContext(), "Damian Tracz damianotracz@gmail.com", 0).show();
				return true;
			case R.id.iPreferences:
				Intent i = new Intent(this, PreferencesScreen.class);
				startActivity(i);
				return true;
			default:
				return super.onOptionsItemSelected(item);
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
    
    @Override
    public void onBackPressed(){
    	finish();
    }
    
}
