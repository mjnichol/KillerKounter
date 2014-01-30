package com.killerkounter;

import java.util.ArrayList;

public class CounterList {

	private int numCounters;
	private ArrayList<Counter> counterList;
	
	// default constructor when no save file is present
	
	// might need to catch an exception if there is no save data
	public CounterList(){
		numCounters = 0;
		counterList = new ArrayList<Counter>();
	}
	
	// constructor when we can extract the counter list from a save file
	public CounterList(ArrayList<Counter> savedList){
		counterList = savedList; // assign the counter list to the old save state
		numCounters = counterList.size();
	}
	
	// add a new counter to the list
	public void addCounter(Counter counter){
		numCounters++;
		counterList.add(counter);
	}
	
	// get the number of counters
	public int getNumCounters() {
		return numCounters;
	}

	// get the list of counters
	public ArrayList<Counter> getCounterList() {
		return counterList;
	}

	// Remove a counter from the list
	public void destroyCounter(Counter counter){
		counterList.remove(counter);
	}
	
}
