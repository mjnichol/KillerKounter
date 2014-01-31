package com.killerkounter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;

/* 
 * This activity will make the main splash page that
 * will be used for navigating between the options we have available
*/
public class MainActivity extends Activity {
	
	// key for passing counter list
	public final static String COUNTER_LIST = "com.killercounter.COUNTER_LIST";
	
	// list to hold the counters (add a load and save feature later
	// see lonely twitter for help!
	public CounterList counters = new CounterList();
	

	
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
	public void seeCounters(View view){ // might want to pass the counter list into this?
		// transfer to the counter activity
		Intent intent = new Intent(this, DisplayCountersActivity.class);
		
		// String to hold the JSON string
		String JSON_CounterList = new Gson().toJson(counters);
		
		// send the JSON string
		intent.putExtra(COUNTER_LIST, JSON_CounterList);
		
		// start the desired activity
		startActivity(intent);
	}
	
	
	public void seeStats(View view){
		// perform the logic to transfer to the counter activity
		// make a toast to check that this button press works
		 Toast.makeText(this,
	                "Clicked see stats",
	                Toast.LENGTH_SHORT).show();
	}
	
	/* Called when the user clicks the send button */
	public void createCounter(View view){
		Toast.makeText(this,
                "Clicked create counter",
                Toast.LENGTH_SHORT).show();
		EditText editText = (EditText) findViewById(R.id.edit_message);
		//String name = editText.getText().toString();
		//counters.addCounter(new Counter(name));
	}
}
	

	
	

