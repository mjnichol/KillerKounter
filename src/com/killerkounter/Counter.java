package com.killerkounter;

import java.util.ArrayList;
import java.util.Date;

public class Counter {

	private Integer count;
	private String name;
	private ArrayList<Date> dates;
	
	// add an exception to catch null names and repeated names
	public Counter(String name) {
		this.count = 0;
		this.name = name;
		dates = new ArrayList<Date>();
	}
	
	// Reset the counter
	public void Reset(){
		count = 0;
		dates = new ArrayList<Date>();
	}
	
	// add an exception if the string is Null
	public void Rename(String name){
		this.name = name;
	}
	
	// Increment the counter
	public void Increment(){
		count++;
		dates.add(new Date());
	}

	// Below are the getters
	public Integer getCount() {
		return count;
	}

	// Get the counter name
	public String getName() {
		return name;
	}

	// Get the list of dates. Will be used for statistics
	public ArrayList<Date> getDates() {
		return dates;
	}

}
