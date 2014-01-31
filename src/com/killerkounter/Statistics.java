package com.killerkounter;

import java.util.ArrayList;
import java.util.Date;


// This class will handle all of the statistics functionalities for the application
public class Statistics {


	// Take a counter only! The user will have to select a counter
	// This will then take them to a new view
	
	private Counter counter;
	// default constructor
	public Statistics(Counter counter) {
		this.counter = counter;
	}
	
	/*
	The below methods all return an array list of strings which lists
	the counts for each time unit in the range
	*/
	
	public ArrayList<String> MinutelyCounts(){
		ArrayList<String> min_counts = new ArrayList<String>();
		
		int count = 0;
		
		// make sure there are counts for the counter
		if (counter.getDates() == null)
			return null;
		
		Date temp = counter.getDates().get(0);
		
		
		// iterate over the dates
		for(Date date : counter.getDates()){
			
			// if the hour switches add to the string and refresh count
			if(temp.getHours() < date.getHours() && !(date.equals(temp))){
				
			}
			
		}
		
		return min_counts;		
	}

	public ArrayList<String> HourlyCounts(){
		ArrayList<String> hour_counts = new ArrayList<String>();
		
		return hour_counts;		
	}
	
	public ArrayList<String> DailyCounts(){
		ArrayList<String> day_counts = new ArrayList<String>();
		
		return day_counts;		
	}
	
	public ArrayList<String> WeeklyCounts(){
		ArrayList<String> week_counts = new ArrayList<String>();
		
		return week_counts;		
	}
	
	public ArrayList<String> MonthlyCounts(){
		ArrayList<String> month_counts = new ArrayList<String>();
		
		return month_counts;		
	}
	
	
}
