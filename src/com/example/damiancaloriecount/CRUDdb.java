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
		//dbHelper.onUpgrade(ourDatabase,1, 2);
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
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		String strDate = sdf.format(cal.getTime());
		
		String[] columns = new String[]{DBHelper.COLUMN_DIARY_ID , DBHelper.COLUMN_DIARY_DATE, DBHelper.COLUMN_DIARY_PRODUCT_NAME, DBHelper.COLUMN_DIARY_CARBS, DBHelper.COLUMN_DIARY_PROTEIN, DBHelper.COLUMN_DIARY_FAT, DBHelper.COLUMN_DIARY_KCAL};
		Cursor c = ourDatabase.query(DBHelper.TABLE_DIARY, columns, "date='"+strDate+"'", null, null, null, null);
		
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
			product.setId(c.getInt(iProduct_ID));
			product.setName(c.getString(iProduct_Name));					//change this	
			product.setCarbs(Float.parseFloat(c.getString(iProduct_Carbs)));  //c.getFloat(column_id);
			product.setProtein(Float.parseFloat(c.getString(iProduct_Protein)));
			product.setFat(Float.parseFloat(c.getString(iProduct_Fat)));
			product.setKcal(Float.parseFloat(c.getString(iProduct_Kcal)));
			
			info.add(product);
		}
		
		return info;
	}
	
	public float getTodaysCarbs(){     //return todays carbs form diary database
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		String strDate = sdf.format(cal.getTime());
		
		Cursor c = ourDatabase.rawQuery("SELECT SUM("+DBHelper.COLUMN_DIARY_CARBS+") FROM "+DBHelper.TABLE_DIARY+" WHERE date = '"+strDate+"' ", null);
		if(c.moveToFirst()){
			return c.getFloat(0);
		} else{
			return 0;
		}			
	}
	
	public float getTodaysProtein(){     //return todays protein form diary database
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		String strDate = sdf.format(cal.getTime());
		
		Cursor c = ourDatabase.rawQuery("SELECT SUM("+DBHelper.COLUMN_DIARY_PROTEIN+") FROM "+DBHelper.TABLE_DIARY+" WHERE date = '"+strDate+"' ", null);
		if(c.moveToFirst()){
			return c.getFloat(0);
		} else{
			return 0;
		}			
	}
	
	public float getTodaysFat(){     //return todays fat form diary database
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
		String strDate = sdf.format(cal.getTime());
		
		Cursor c = ourDatabase.rawQuery("SELECT SUM("+DBHelper.COLUMN_DIARY_FAT+") FROM "+DBHelper.TABLE_DIARY+" WHERE date = '"+strDate+"' ", null);
		if(c.moveToFirst()){
			return c.getFloat(0);
		} else{
			return 0;
		}			
	}
	
	public long deleteProductFromDiary(int id){
		String selection = DBHelper.COLUMN_DIARY_ID + " LIKE ?";
		String[] selectionArgs = {String.valueOf(id)};
		return ourDatabase.delete(DBHelper.TABLE_DIARY, selection, selectionArgs);
	}
	
	public void insertData(String str){
		ourDatabase.execSQL(str);
	}
	
	/*
	 * Return all product, use for example to export to csv
	 */
	public ArrayList<Product> getAllProductFromDb(){
		String[] columns = new String[]{DBHelper.COLUMN_PRODUCTS_ID, DBHelper.COLUMN_PRODUCTS_PRODUCT_NAME, DBHelper.COLUMN_PRODUCTS_CARBS, DBHelper.COLUMN_PRODUCTS_PROTEIN, DBHelper.COLUMN_PRODUCTS_FAT, DBHelper.COLUMN_PRODUCTS_KCAL};
		Cursor c = ourDatabase.query(DBHelper.TABLE_PRODUCTS, columns, null, null, null, null, null);
		
		int iProduct_ID = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_ID);
		int iProduct_Name = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_PRODUCT_NAME);
		int iProduct_Carbs = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_CARBS);
		int iProduct_Protein = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_PROTEIN);
		int iProduct_Fat = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_FAT);
		int iProduct_Kcal = c.getColumnIndex(DBHelper.COLUMN_PRODUCTS_KCAL);
		
		ArrayList<Product> info = new ArrayList<Product>();
		
		// make this better, loop only one time
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			Product product = new Product();
			product.setId(c.getInt(iProduct_ID));
			product.setName(c.getString(iProduct_Name));					//change this	
			product.setCarbs(Float.parseFloat(c.getString(iProduct_Carbs)));  //c.getFloat(column_id);
			product.setProtein(Float.parseFloat(c.getString(iProduct_Protein)));
			product.setFat(Float.parseFloat(c.getString(iProduct_Fat)));
			product.setKcal(Float.parseFloat(c.getString(iProduct_Kcal)));
			
			info.add(product);
		}
		
		return info; 		
	}
	
}
