package com.example.damiancaloriecount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddToDatabaseFromCSV extends Activity implements OnClickListener{

	EditText etFileName;
	Button bStartImport;
	CRUDdb database;
	public static final File sdCard = Environment.getExternalStorageDirectory();
	public static final String sdcardBaseDir = sdCard.getAbsolutePath();
	public static final String externalPath = "/Android/data/com.example/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_to_database_from_csv_screen);
		
		etFileName = (EditText)findViewById(R.id.etFileName);
		bStartImport = (Button)findViewById(R.id.bStartImport);
		
		database = new CRUDdb(this);
		database.open();
		
		bStartImport.setOnClickListener(this);		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bStartImport:
			String fileName = etFileName.getText().toString();
			Toast.makeText(getApplicationContext(),fileName, 0).show();
			importToDB(fileName);
			
			break;
		}
	}
	
	void importToDB(String name){
		try {
			Toast.makeText(getApplicationContext(),"Import...", 0).show();
			FileReader file = new FileReader(sdcardBaseDir + externalPath + name);
			BufferedReader buffer = new BufferedReader(file);
			String line ="";
			String tableName = DBHelper.TABLE_PRODUCTS;
			String columns = "product_name, carbs, protein, fat, kcal";
			String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
			String str2 = ");";
			int counter = 0;
			
			/*
			 INSERT INTO myTable(columns...)
			Select values...
			WHERE NOT EXISTS
			   (SELECT *
			    FROM myTable
			    WHERE pk_part1 = value1,
			        AND pk_part2 = value2)
			 */
			
			while ((line = buffer.readLine()) != null) {
				StringBuilder sb = new StringBuilder(str1);
				String[] str = line.split(",");
				if(str.length == 5){
					sb.append("'" + str[1] + "',");
					sb.append(str[2] + ",");
					sb.append(str[3] + ",");
					sb.append(str[4] + ",");
					float kcal = (Float.valueOf(str[2]) * 4) +  (Float.valueOf(str[3]) * 4) + (Float.valueOf(str[4]) * 9); 	
					sb.append(String.valueOf(kcal));
					sb.append(str2);
					System.out.println(sb.toString());
					database.insertData(sb.toString());  
					counter++;
				}
			}
			Toast.makeText(getApplicationContext(),"Added "+ String.valueOf(counter) +" elements", 0).show();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			name = "error";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		database.open();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		database.close();
	}
	
}
