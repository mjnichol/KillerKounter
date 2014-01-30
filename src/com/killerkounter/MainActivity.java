package com.killerkounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

/* 
 * This activity will make the main splash page that
 * will be used for navigating between the options we have available
*/
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	/* Called when the user wants to go to the counter page */
	public void seeCounters(View view){
		// perform the logic to transfer to the counter activity
		// make a toast to check that this button press works
	}
	
	public void seeStats(View view){
		// perform the logic to transfer to the counter activity
		// make a toast to check that this button press works
	}
	
	// Will their be a create counters activity? Unlikely, do it inline
	/*
	public void createCounters(View view){
		
	}
	*/
	
	
}
