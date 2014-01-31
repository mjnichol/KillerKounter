package com.killerkounter;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import java.util.Collection;

/**
 * @author  mjnichol
 */
public class DisplayStatisticsActivity extends ListActivity {

	public final static String NEW_COUNTER_LIST = "com.killercounter.NEW_COUNTER_LIST";
	public final static String STAT_LIST = "com.killercounter.STAT_LIST";
	
	private ArrayAdapter<Counter> mAdapter;
	/**
	 * @uml.property  name="my_counters"
	 * @uml.associationEnd  
	 */
	private CounterList my_counters;
	
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
		setContentView(R.layout.activity_display_statistics);
		
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
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
	}
	
	
    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        
    	Counter instance = (Counter) getListAdapter().getItem(position);
    	Statistics stats = new Statistics(instance);
    	
    	// create a new activity that is passed a JSONified statistic determined by the counter
		// transfer to the counter activity
		Intent intent = new Intent(this, DisplayTimeActivity.class);

		// String to hold the JSON string
		String JSON_StatList = new Gson().toJson(stats);

		// send the JSON string
		intent.putExtra(STAT_LIST, JSON_StatList);
		startActivity(intent);

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
		getMenuInflater().inflate(R.menu.display_statistics, menu);
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** 
	 * @uml.property name="displayTimeActivity"
	 * @uml.associationEnd multiplicity="(0 -1)" inverse="displayStatisticsActivity:com.killerkounter.DisplayTimeActivity"
	 */
	private Collection<DisplayTimeActivity> displayTimeActivity;

	/** 
	 * Getter of the property <tt>displayTimeActivity</tt>
	 * @return  Returns the displayTimeActivity.
	 * @uml.property  name="displayTimeActivity"
	 */
	public Collection<DisplayTimeActivity> getDisplayTimeActivity() {
		return displayTimeActivity;
	}


	/** 
	 * Setter of the property <tt>displayTimeActivity</tt>
	 * @param displayTimeActivity  The displayTimeActivity to set.
	 * @uml.property  name="displayTimeActivity"
	 */
	public void setDisplayTimeActivity(
			Collection<DisplayTimeActivity> displayTimeActivity) {
		this.displayTimeActivity = displayTimeActivity;
	}

}
