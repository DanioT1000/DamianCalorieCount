package com.example.damiancaloriecount;

public class Product {
	
	//private variables
	int id;
	String name;
	float carbs;
	float protein;
	float fat;
	float kcal;
	
	public Product(){
		
	}
	
	public Product(int id, String name, float carbs, float protein, float fat, float kcal){
		this.id = id;
		this.name = name;
		this.carbs = carbs;
		this.protein = protein;
		this.fat = fat;
		this.kcal = kcal;
	}
	//set
	public void setId(int id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setCarbs(float carbs){
		this.carbs = carbs;
	}
	
	public void setProtein(float protein){
		this.protein = protein;
	}
	
	public void setFat(float fat){
		this.fat = fat;
	}
	
	public void setKcal(float kcal){
		this.kcal = kcal;
	}
	//get
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public float getCarbs(){
		return this.carbs;
	}
	
	public float getProtein(){
		return this.protein;
	}
	
	public float getFat(){
		return this.fat;
	}
	
	public float getKcal(){
		return this.kcal;
	}
	
}
