package com.example.damiancaloriecount;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ListTodaysProductScreen extends Activity {

	CRUDdb database;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_todays_product_screen);
		
		//read from database
		database = new CRUDdb(this);
		database.open();
		
		ArrayList<Product> products = database.getAllProductsToday();
		
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        TableRow tbrow0 = new TableRow(this);
        	TextView tv0 = new TextView(this);
        	tv0.setText(" Name");
        	tv0.setTextColor(Color.WHITE);
        	tv0.setGravity(Gravity.CENTER);
        	tbrow0.addView(tv0);
        	
        	TextView tv1 = new TextView(this);
        	tv1.setText(" Carbs ");
	        tv1.setTextColor(Color.WHITE);
	        tv1.setGravity(Gravity.CENTER);
	        tbrow0.addView(tv1);
	        
	        TextView tv2 = new TextView(this);
	        tv2.setText(" Protein ");
	        tv2.setTextColor(Color.WHITE);
	        tv2.setGravity(Gravity.CENTER);
	        tbrow0.addView(tv2);
	        
	        TextView tv3 = new TextView(this);
	        tv3.setText(" Fat ");
	        tv3.setTextColor(Color.WHITE);
	        tv3.setGravity(Gravity.CENTER);
	        tbrow0.addView(tv3);
	        
	        TextView tv4 = new TextView(this);
	        tv4.setText("Kcal");
	        tv4.setTextColor(Color.WHITE);
	        tv4.setGravity(Gravity.CENTER);
	        tbrow0.addView(tv4);
	        
	        TextView tv5 = new TextView(this);
	        tv4.setText("Id");
	        tv4.setTextColor(Color.WHITE);
	        tv4.setGravity(Gravity.CENTER);
	        tbrow0.addView(tv5);
	        
	        stk.addView(tbrow0);
        
        
        
        for (Product prod : products) {
            TableRow tbrow = new TableRow(this);
            tbrow.setClickable(true);
	            TextView t0v = new TextView(this);
	            t0v.setText(prod.getName());
	            t0v.setTextColor(Color.WHITE);
	            t0v.setGravity(Gravity.CENTER);
	            tbrow.addView(t0v);
	            
	            TextView t1v = new TextView(this);
	            t1v.setText(String.valueOf(prod.getCarbs()));
	            t1v.setTextColor(Color.WHITE);
	            t1v.setGravity(Gravity.CENTER);
	            tbrow.addView(t1v);
	            
	            TextView t2v = new TextView(this);
	            t2v.setText(String.valueOf(prod.getProtein()));
	            t2v.setTextColor(Color.WHITE);
	            t2v.setGravity(Gravity.CENTER);
	            tbrow.addView(t2v);
	            
	            TextView t3v = new TextView(this);
	            t3v.setText(String.valueOf(prod.getFat()));
	            t3v.setTextColor(Color.WHITE);
	            t3v.setGravity(Gravity.CENTER);
	            tbrow.addView(t3v);
	            
	            TextView t4v = new TextView(this);
	            t4v.setText(String.valueOf(prod.getKcal()));
	            t4v.setTextColor(Color.WHITE);
	            t4v.setGravity(Gravity.CENTER);
	            tbrow.addView(t4v);
	            
	            TextView t5v = new TextView(this);
	            t4v.setText(String.valueOf(prod.getId()));
	            t4v.setTextColor(Color.WHITE);
	            t4v.setGravity(Gravity.CENTER);
	            tbrow.addView(t5v);
	            
	            stk.addView(tbrow);
	            
	            tbrow.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						v.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
						TableRow tr = (TableRow) v;
						TextView firstTextView = (TextView) tr.getChildAt(4);
						String firstText = firstTextView.getText().toString();
						createDialogAndDeleteFromDB(Integer.parseInt(firstText));						
					}
				});             
		}       
	}
	
	public void createDialogAndDeleteFromDB(final int id){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder
		.setTitle("Delete form diary?")
		.setMessage("Are you sure?")
		.setIcon(android.R.drawable.ic_dialog_alert);
		alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				database.deleteProductFromDiary(id);
				//refresh activity
				Intent intent = getIntent();
				finish();
				startActivity(intent);
			}
		});
		alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
			}
		});
		alertDialogBuilder.show();
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
