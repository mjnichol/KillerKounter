package com.killerkounter;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * @author  mjnichol
 */
public class DisplayCountersActivity extends ListActivity {
	
	public final static String NEW_COUNTER_LIST = "com.killercounter.NEW_COUNTER_LIST";
	
	// array adapter to notice changes to the counter list
	private ArrayAdapter<Counter> mAdapter;
	/** 
	 * @uml.property name="my_counters"
	 * @uml.associationEnd 
	 */
	private CounterList my_counters;
	
	// button switches
	private boolean rename = false;
	private boolean reset = false;
	
	Intent returnIntent = new Intent();
	
	// turn the reset button on and off
	public void toggleReset(View view){
		this.reset = !this.reset;
	}
	
	// turn the rename button on and off
	public void toggleRename(View view){
		this.rename = !this.rename;
	}
	
	// intercept the back button signal and store counter state
	@Override
	public void onBackPressed() {
		Toast.makeText(this,
				"pressed back",
				Toast.LENGTH_SHORT).show();
		
		//save the counters
    	// make the new JSON for the counters
    	Gson gson = new Gson();
		// extract the JSON
		String jsonCounters = gson.toJson(my_counters);
    	
    	// Now send back the data to the parent activity?
    	
    	returnIntent.putExtra("countUpdate", jsonCounters);
    	setResult(RESULT_OK, returnIntent);
    	// do I add finish() now?
    	finish();
	}
	
	// intercept the key down button signal to save counter state
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK ) {
	        //save the counters
	    	// make the new JSON for the counters
	    	Gson gson = new Gson();
			// extract the JSON
			String jsonCounters = gson.toJson(my_counters);
	    	
	    	// Now send back the data to the parent activity?
	    	
	    	returnIntent.putExtra("countUpdate", jsonCounters);
	    	setResult(RESULT_OK, returnIntent);
	    	// do I add finish() now?
	    	finish();
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// get the intent passed in
		Intent intent = getIntent();
		String CounterListJSON = intent.getStringExtra(MainActivity.COUNTER_LIST);
		
		// Gson element
		Gson gson = new Gson();
		// extract the JSON
		CounterList counters = gson.fromJson(CounterListJSON, CounterList.class);
		this.my_counters = counters;
		
		//you will have to use the swipe to unlock XML stuff to make this work as desired
		setContentView(R.layout.activity_display_counters);
		
		// Show the Up button in the action bar.
		setupActionBar();
		
        mAdapter = new ArrayAdapter<Counter>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                counters.getCounterList());
                //new ArrayList<Counter>(Arrays.asList(items))); // pass the counter list here, overload the toString method so that things are displayed correctly
        setListAdapter(mAdapter);

        ListView listView = getListView();
		
     // Create a ListView-specific touch listener. ListViews are given special treatment because
        // by default they handle touches for their list items... i.e. they're in charge of drawing
        // the pressed state (the list selector), handling list item clicks, etc.
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        listView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mAdapter.remove(mAdapter.getItem(position));
                                }
                                // call the counter list remove function here
                                mAdapter.notifyDataSetChanged();
                            }
                        });
        listView.setOnTouchListener(touchListener);
        // Setting this scroll listener is required to ensure that during ListView scrolling,
        // we don't look for swipes.
        listView.setOnScrollListener(touchListener.makeScrollListener());
        
        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true); //was tru, but force user to use back to navigate
        }
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_counters, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			//NavUtils.navigateUpFromSameTask(this);
			
			//save the counters
	    	// make the new JSON for the counters
	    	Gson gson = new Gson();
			// extract the JSON
			String jsonCounters = gson.toJson(my_counters);
	    	returnIntent.putExtra("countUpdate", jsonCounters);
	    	setResult(RESULT_OK, returnIntent);
	    	finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        
    	
    	if(reset){
    		my_counters.getCounterList().get(position).Reset();
    		reset = !reset;
    	}
    	
    	else if(rename){
    		EditText editText = (EditText) findViewById(R.id.edit_name);
    		String name = editText.getText().toString();
    		my_counters.getCounterList().get(position).Rename(name);
    		rename = !rename;
    	}
    	
    	else{
    		my_counters.getCounterList().get(position).Increment();
    	}

    	mAdapter.notifyDataSetChanged();
    	onContentChanged(); // this causes the place in the list to go to the top
 
    }
}
