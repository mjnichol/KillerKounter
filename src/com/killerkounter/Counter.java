package com.killerkounter;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author  mjnichol
 */
public class Counter {

	/**
	 * @uml.property  name="count"
	 */
	private Integer count;
	/**
	 * @uml.property  name="name"
	 */
	private String name;
	/**
	 * @uml.property  name="dates"
	 */
	private ArrayList<Date> dates;
	
	// add an exception to catch null names and repeated names
	public Counter(String name) throws IllegalArgumentException {
		
		// make sure the counter's name isn't empty!
		if (name == null)
			throw new IllegalArgumentException();
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
	public void Rename(String name) throws IllegalArgumentException{
		// make sure the counter's name isn't empty!
		if (name == null)
			throw new IllegalArgumentException();
		this.name = name;
	}
	
	// Increment the counter
	public void Increment(){
		count++;
		dates.add(new Date());
	}

	// Below are the getters
	/**
	 * @return
	 * @uml.property  name="count"
	 */
	public Integer getCount() {
		return count;
	}

	// Get the counter name
	/**
	 * @return
	 * @uml.property  name="name"
	 */
	public String getName() {
		return name;
	}

	// Get the list of dates. Will be used for statistics
	/**
	 * @return
	 * @uml.property  name="dates"
	 */
	public ArrayList<Date> getDates() {
		return dates;
	}
	
	// You will want to print the count and name here. This code is interpreted by the listview in the counter activity
	@Override
	public String toString(){
		return count.toString() + "\n" + name; 
	}
	

}
