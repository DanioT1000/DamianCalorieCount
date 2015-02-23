package com.example.damiancaloriecount;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CRUDdb {
	
	private DBHelper dbHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	public CRUDdb(Context c){
		ourContext = c;
	}
	
	public CRUDdb open() {
		dbHelper = new DBHelper(ourContext);
		ourDatabase = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		dbHelper.close();
	}
	
	public long createEntry(String product_name, Float carbs, Float protein, Float fat, Float kcal){
		ContentValues cv = new ContentValues();
		cv.put(DBHelper.COLUMN_DIARY_PRODUCT_NAME, product_name);
		cv.put(DBHelper.COLUMN_PRODUCTS_CARBS, carbs);
		cv.put(DBHelper.COLUMN_PRODUCTS_PROTEIN, protein);
		cv.put(DBHelper.COLUMN_PRODUCTS_FAT, fat);
		cv.put(DBHelper.COLUMN_PRODUCTS_KCAL, kcal);
		return ourDatabase.insert(DBHelper.TABLE_PRODUCTS, null, cv);	
	}
	
	public String getData(){
		String[] columns = new String[]{DBHelper.COLUMN_PRODUCTS_ID, DBHelper.COLUMN_PRODUCTS_PRODUCT_NAME, DBHelper.COLUMN_PRODUCTS_CARBS, DBHelper.COLUMN_PRODUCTS_PROTEIN, DBHelper.COLUMN_PRODUCTS_FAT, DBHelper.COLUMN_PRODUCTS_KCAL};
		Cursor c = ourDatabase.query(DBHelper.TABLE_PRODUCTS, columns, null, null, null, null, null);
		String result ="";
		
		int iProduct_ID = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_ID);
		int iProduct_Name = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_PRODUCT_NAME);
		int iProduct_Carbs = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_CARBS);
		int iProduct_Protein = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_PROTEIN);
		int iProduct_Fat = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_FAT);
		int iProduct_Kcal = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_KCAL);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iProduct_ID)+c.getString(iProduct_Name)+c.getString(iProduct_Carbs)+c.getString(iProduct_Protein)+c.getString(iProduct_Fat)+c.getString(iProduct_Kcal);
		}
	
		return result;
	}
	
	public ArrayList<String> getDataArrayList(){
		String[] columns = new String[]{DBHelper.COLUMN_PRODUCTS_ID, DBHelper.COLUMN_PRODUCTS_PRODUCT_NAME, DBHelper.COLUMN_PRODUCTS_CARBS, DBHelper.COLUMN_PRODUCTS_PROTEIN, DBHelper.COLUMN_PRODUCTS_FAT, DBHelper.COLUMN_PRODUCTS_KCAL};
		Cursor c = ourDatabase.query(DBHelper.TABLE_PRODUCTS, columns,null, null, null, null, null);
		ArrayList names = new ArrayList<String>();
		
		int iProduct_ID = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_ID);
		int iProduct_Name = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_PRODUCT_NAME);
		int iProduct_Carbs = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_CARBS);
		int iProduct_Protein = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_PROTEIN);
		int iProduct_Fat = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_FAT);
		int iProduct_Kcal = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_KCAL);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			names.add(c.getString(iProduct_Name));
		}
		
		return names;	
	}
	
	public ArrayList<String> searchDataAndReturnArrayList(String productName){
		String[] columns = new String[]{DBHelper.COLUMN_PRODUCTS_ID, DBHelper.COLUMN_PRODUCTS_PRODUCT_NAME, DBHelper.COLUMN_PRODUCTS_CARBS, DBHelper.COLUMN_PRODUCTS_PROTEIN, DBHelper.COLUMN_PRODUCTS_FAT, DBHelper.COLUMN_PRODUCTS_KCAL};
		Cursor c = ourDatabase.query(DBHelper.TABLE_PRODUCTS, columns, "product_name='"+productName+"'", null, null, null, null);
		
		ArrayList names = new ArrayList<String>();
		
		int iProduct_ID = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_ID);
		int iProduct_Name = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_PRODUCT_NAME);
		int iProduct_Carbs = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_CARBS);
		int iProduct_Protein = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_PROTEIN);
		int iProduct_Fat = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_FAT);
		int iProduct_Kcal = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_KCAL);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			names.add(c.getString(iProduct_Name));
		}
		
		return names;
	}
	
	public ArrayList<String> getDataAboutProduct(String productName){
		String[] columns = new String[]{DBHelper.COLUMN_PRODUCTS_ID, DBHelper.COLUMN_PRODUCTS_PRODUCT_NAME, DBHelper.COLUMN_PRODUCTS_CARBS, DBHelper.COLUMN_PRODUCTS_PROTEIN, DBHelper.COLUMN_PRODUCTS_FAT, DBHelper.COLUMN_PRODUCTS_KCAL};
		Cursor c = ourDatabase.query(DBHelper.TABLE_PRODUCTS, columns, "product_name='"+productName+"'", null, null, null, null);
		
		ArrayList info = new ArrayList<String>();
		
		int iProduct_ID = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_ID);
		int iProduct_Name = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_PRODUCT_NAME);
		int iProduct_Carbs = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_CARBS);
		int iProduct_Protein = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_PROTEIN);
		int iProduct_Fat = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_FAT);
		int iProduct_Kcal = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_KCAL);
		
		// make this better, loop only one time
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			info.add(c.getString(iProduct_Name));
			info.add(c.getString(iProduct_Carbs));
			info.add(c.getString(iProduct_Protein));
			info.add(c.getString(iProduct_Fat));
			info.add(c.getString(iProduct_Kcal));
		}
		
		return info;
	}
	
	public long addToDiary(String product_name, float carbs, float protein, float fat, float kcal){
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		String strDate = sdf.format(c.getTime());
		
		ContentValues cv = new ContentValues();
		cv.put(DBHelper.COLUMN_DIARY_DATE, strDate);
		cv.put(DBHelper.COLUMN_DIARY_PRODUCT_NAME, product_name);
		cv.put(DBHelper.COLUMN_DIARY_CARBS, carbs);
		cv.put(DBHelper.COLUMN_DIARY_PROTEIN, protein);
		cv.put(DBHelper.COLUMN_DIARY_FAT , fat);
		cv.put(DBHelper.COLUMN_DIARY_KCAL, kcal);
		return ourDatabase.insert(DBHelper.TABLE_DIARY, null, cv);	
	}
	
	public ArrayList<Product> getAllProductsToday(){
		String[] columns = new String[]{DBHelper.COLUMN_DIARY_ID , DBHelper.COLUMN_DIARY_DATE, DBHelper.COLUMN_DIARY_PRODUCT_NAME, DBHelper.COLUMN_DIARY_CARBS, DBHelper.COLUMN_DIARY_PROTEIN, DBHelper.COLUMN_DIARY_FAT, DBHelper.COLUMN_DIARY_KCAL};
		Cursor c = ourDatabase.query(DBHelper.TABLE_DIARY, columns, null, null, null, null, null);
		
		ArrayList<Product> info = new ArrayList<Product>();
		
		int iProduct_ID = c.getColumnIndex(DBHelper.COLUMN_DIARY_ID);
		int iProduct_Date = c.getColumnIndex(DBHelper.COLUMN_DIARY_DATE);
		int iProduct_Name = c.getColumnIndex(DBHelper.COLUMN_DIARY_PRODUCT_NAME);
		int iProduct_Carbs = c.getColumnIndex(DBHelper.COLUMN_DIARY_CARBS);
		int iProduct_Protein = c.getColumnIndex(DBHelper.COLUMN_DIARY_PROTEIN);
		int iProduct_Fat = c.getColumnIndex(DBHelper.COLUMN_DIARY_FAT);
		int iProduct_Kcal = c.getColumnIndex(DBHelper.COLUMN_DIARY_KCAL);
		
		// make this better, loop only one time
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			Product product = new Product();
			product.setName(c.getString(iProduct_Name));
			product.setCarbs(Float.parseFloat(c.getString(iProduct_Carbs)));
			product.setProtein(Float.parseFloat(c.getString(iProduct_Protein)));
			product.setFat(Float.parseFloat(c.getString(iProduct_Fat)));
			product.setKcal(Float.parseFloat(c.getString(iProduct_Kcal)));
			
			info.add(product);
		}
		
		return info;
	}
	
}
