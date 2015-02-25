package com.example.damiancaloriecount;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
   private static final String DATABASE_NAME = "calorieCount.db";
   private static final int DATABASE_VERSION = 1;
   
   //table products
   public static final String TABLE_PRODUCTS = "products";
   public static final String COLUMN_PRODUCTS_ID = "_id";
   public static final String COLUMN_PRODUCTS_PRODUCT_NAME = "product_name";
   public static final String COLUMN_PRODUCTS_CARBS = "carbs";
   public static final String COLUMN_PRODUCTS_PROTEIN = "protein";
   public static final String COLUMN_PRODUCTS_FAT = "fat";
   public static final String COLUMN_PRODUCTS_KCAL = "kcal";
   
   //table products
   public static final String TABLE_DIARY = "diary";
   public static final String COLUMN_DIARY_ID = "_id";
   public static final String COLUMN_DIARY_DATE = "date";
   public static final String COLUMN_DIARY_PRODUCT_NAME = "product_name";
   public static final String COLUMN_DIARY_CARBS = "carbs";
   public static final String COLUMN_DIARY_PROTEIN = "protein";
   public static final String COLUMN_DIARY_FAT = "fat";
   public static final String COLUMN_DIARY_KCAL = "kcal";
   
   //SQL statement of the employess table creation
   private static final String SQL_CREATE_TABLE_PRODUCTS = "CREATE TABLE " + TABLE_PRODUCTS + " ("
		+ COLUMN_PRODUCTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ COLUMN_PRODUCTS_PRODUCT_NAME +" TEXT NOT NULL, "
		+ COLUMN_PRODUCTS_CARBS +" FLOAT NOT NULL, "
		+ COLUMN_PRODUCTS_PROTEIN +" FLOAT NOT NULL, "
		+ COLUMN_PRODUCTS_FAT  +" FLOAT NOT NULL, "
		+ COLUMN_PRODUCTS_KCAL +" FLOAT NOT NULL);";
   
   // CREATE SECOND STRING HERE
   private static final String SQL_CREATE_TABLE_DIARY = "CREATE TABLE " + TABLE_DIARY + " ("
			+ COLUMN_DIARY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ COLUMN_DIARY_DATE + " DATE NOT NULL, "
			+ COLUMN_DIARY_PRODUCT_NAME +" TEXT NOT NULL, "
			+ COLUMN_DIARY_CARBS +" FLOAT NOT NULL, "
			+ COLUMN_DIARY_PROTEIN +" FLOAT NOT NULL, "
			+ COLUMN_DIARY_FAT  +" FLOAT NOT NULL, "
			+ COLUMN_DIARY_KCAL +" FLOAT NOT NULL);";
  

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(SQL_CREATE_TABLE_PRODUCTS);
		db.execSQL(SQL_CREATE_TABLE_DIARY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY);
		
		//recreate the tables
		onCreate(db);
	}

}
