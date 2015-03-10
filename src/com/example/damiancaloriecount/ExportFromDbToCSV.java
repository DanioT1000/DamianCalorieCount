package com.example.damiancaloriecount;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import ar.com.daidalos.afiledialog.FileChooserDialog;

public class ExportFromDbToCSV extends Activity {

	Button bStartExport;
	CRUDdb database;
	FileChooserDialog dialog;
	public static final File sdCard = Environment.getExternalStorageDirectory();
	public static final String sdcardBaseDir = sdCard.getAbsolutePath();
	public static final String externalPath = "/Android/data/com.example/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_export_from_db_to_csv);
		bStartExport = (Button) findViewById(R.id.bStartExport);
		
		//open database
		database = new CRUDdb(this);
		database.open();
		
		//file dialog
		 dialog =  new FileChooserDialog(this);
				
		bStartExport.setOnClickListener(new View.OnClickListener() {		
			public void onClick(View v) {
				openFileDialogAndSave();
			}
		});
		
	}
	
	protected void openFileDialogAndSave(){
	 	dialog.loadFolder(Environment.getExternalStorageDirectory()+ "/Download");
        dialog.setCanCreateFiles(true);
        dialog.setFilter(".*csv");
        dialog.setShowConfirmation(true, false);
        dialog.show();
       
        dialog.addListener(new FileChooserDialog.OnFileSelectedListener() {
			
        	/*
        	 * is called when file is created
        	 */
			@Override
			public void onFileSelected(Dialog source, File folder, String name) {
				// TODO Auto-generated method stub
				source.cancel();
				 //Toast toast = Toast.makeText(source.getContext(), "File selected: " + folder.getAbsolutePath() +"/"+ name, Toast.LENGTH_LONG);
	             //toast.show();
	             String filePath = folder.getAbsolutePath() +"/"+ name;
	             new ExportDatabaseCSVTask().execute(filePath);	
	             	
			}
			
			/*
			 * Is called when a file is selected
			 */
			@Override
			public void onFileSelected(Dialog source, File file) { 
				// TODO Auto-generated method stub
				source.cancel();
				 //Toast toast = Toast.makeText(source.getContext(), "File selected: " + file.getAbsolutePath(), Toast.LENGTH_LONG);
	             //toast.show();
	             new ExportDatabaseCSVTask().execute(file.getAbsolutePath());
			}
		});		
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
	
	/*
	 * Class ExportDatabaseCSVTask
	 */
	private class ExportDatabaseCSVTask extends AsyncTask<String, Void, Void>{
		ProgressDialog dialog = new ProgressDialog(ExportFromDbToCSV.this);
		
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			this.dialog.setMessage("Exporting database ...");
			this.dialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub	
			Toast.makeText(getApplicationContext(), "Exporting complite", 0).show();
			this.dialog.dismiss();
		}

		@Override
		protected void onProgressUpdate(Void... values) {
			// TODO Auto-generated method stub
			
		}
		///storage/sdcard0/Download/test1.txt"
		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			ArrayList<Product> products = database.getAllProductFromDb();  //get all product into array list
			try{
				File file = new File(params[0]); //file name with absolute path
				FileOutputStream fos = new FileOutputStream(file);
				
				for(Product prod: products){
					String line = prod.getId()+ ";" + prod.getName() + ";" + prod.getCarbs() + ";" + prod.getProtein() + ";" + prod.getFat() + ";" + prod.getKcal() + "\n";
					fos.write(line.getBytes());
					fos.flush();
				}
	
				fos.close();
			} catch (Exception e){
				e.printStackTrace();
			}
					
			return null;
		}
		//private final ProgressDialog dialog = new ProgressDialog(getApplicationContext());
		
    
		
	}

}
