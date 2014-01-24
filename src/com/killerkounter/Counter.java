package com.killerkounter;

import java.util.ArrayList;
import java.util.Date;

public class Counter {

	private Integer count;
	private String name;
	private ArrayList<Date> dates;
	
	public Counter(String name) {
		this.count = 0;
		this.name = name;
		dates = new ArrayList<Date>();
	}
	
	public void Reset(){
		count = 0;
		dates = new ArrayList<Date>();
	}
	
	public void Rename(String name){
		this.name = name;
	}
	public void Increment(){
		count++;
		dates.add(new Date());
	}

	// Below are the getters
	public Integer getCount() {
		return count;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Date> getDates() {
		return dates;
	}

}
