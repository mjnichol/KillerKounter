package com.killerkounter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

/* 
 * This activity will make the main splash page that
 * will be used for navigating between the options we have available
 */
/**
 * @author  mjnichol
 */
public class MainActivity extends Activity {

	// Save file
	private static final String FILENAME = "file.sav";

	// Result Code
	private static final int RESULT_CODE = 1;

	// key for passing counter list
	public final static String COUNTER_LIST = "com.killercounter.COUNTER_LIST";

	// list to hold the counters (add a load and save feature later
	// see lonely twitter for help!
	/**
	 * @uml.property  name="counters"
	 * @uml.associationEnd  
	 */
	public CounterList counters = new CounterList();

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		this.loadFromFile();
	}

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

	// load the saved state of the counters
	private void loadFromFile(){

		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			Gson gson = new Gson();
			while (line != null) {
				counters = gson.fromJson(line, CounterList.class);
				line = in.readLine();
			}          
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// save all the counters to the file (need to clear the save file and rewrite to it)
	private void saveToFile(){
		deleteFile(FILENAME);
		try {
			//countersArray.add(counter);
			Gson gson = new Gson();
			String json = gson.toJson(counters); //should this be the getCounterList()?
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write(json.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
		startActivityForResult(intent, RESULT_CODE);
	}
	
	// transfer to the statistics activity
	public void seeStats(View view){

		// transfer to the counter activity
		Intent intent = new Intent(this, DisplayStatisticsActivity.class);

		// String to hold the JSON string
		String JSON_CounterList = new Gson().toJson(counters);

		// send the JSON string
		intent.putExtra(COUNTER_LIST, JSON_CounterList);
		startActivity(intent);
	}

	/* Called when the user clicks the send button */
	public void createCounter(View view){
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String name = editText.getText().toString();
		counters.addCounter(new Counter(name));
		this.saveToFile();
	}

	// okay, this should get the counter object back.
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == RESULT_CODE) {

			// the data is a JSON string that needs to be unpacked
			if(resultCode == RESULT_OK){      
				Gson gson = new Gson();
				String result=data.getStringExtra("countUpdate");
				counters = gson.fromJson(result, CounterList.class);
				this.saveToFile();
				
			}
			if (resultCode == RESULT_CANCELED) {    
				//Write your code if there's no result
			}
		}
	}
}





