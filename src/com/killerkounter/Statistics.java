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
			if(temp.getMinutes() < date.getMinutes() && !(date.equals(temp))){
				min_counts.add(new String(temp + " has " + count + " counts in the minute.\n"));
				count = 0;
			}
			count++;
			temp = date;

		}

		return min_counts;		
	}

	public ArrayList<String> HourlyCounts(){
		ArrayList<String> hour_counts = new ArrayList<String>();

		int count = 0;

		// make sure there are counts for the counter
		if (counter.getDates() == null)
			return null;

		Date temp = counter.getDates().get(0);

		// iterate over the dates
		for(Date date : counter.getDates()){

			// if the hour switches add to the string and refresh count
			if(temp.getHours() < date.getHours() && !(date.equals(temp))){
				hour_counts.add(new String(temp + " has " + count + " counts in the minute.\n"));
				count = 0;
			}
			count++;
			temp = date;

		}

		return hour_counts;		
	}

	public ArrayList<String> DailyCounts(){
		ArrayList<String> day_counts = new ArrayList<String>();

		int count = 0;

		// make sure there are counts for the counter
		if (counter.getDates() == null)
			return null;

		Date temp = counter.getDates().get(0);

		// iterate over the dates
		for(Date date : counter.getDates()){

			// if the hour switches add to the string and refresh count
			if(temp.getDay() < date.getDay() && !(date.equals(temp))){
				day_counts.add(new String(temp + " has " + count + " counts in the minute.\n"));
				count = 0;
			}
			count++;
			temp = date;

		}

		return day_counts;		
	}

	public ArrayList<String> WeeklyCounts(){
		ArrayList<String> week_counts = new ArrayList<String>();

		int count = 0;

		// make sure there are counts for the counter
		if (counter.getDates() == null)
			return null;

		Date temp = counter.getDates().get(0);

		// iterate over the dates
		for(Date date : counter.getDates()){

			// if the hour switches add to the string and refresh count
			if( (temp.getDay())% 7 < (date.getDay()) % 7 && !(date.equals(temp))){
				week_counts.add(new String(temp + " has " + count + " counts in the minute.\n"));
				count = 0;
			}
			count++;
			temp = date;

		}

		return week_counts;		
	}

	public ArrayList<String> MonthlyCounts(){
		ArrayList<String> month_counts = new ArrayList<String>();

		int count = 0;

		// make sure there are counts for the counter
		if (counter.getDates() == null)
			return null;

		Date temp = counter.getDates().get(0);

		// iterate over the dates
		for(Date date : counter.getDates()){

			// if the hour switches add to the string and refresh count
			if( temp.getMonth() < date.getMonth() % 7 && !(date.equals(temp))){
				month_counts.add(new String(temp + " has " + count + " counts in the minute.\n"));
				count = 0;
			}
			count++;
			temp = date;

		}


		return month_counts;		
	}


}
