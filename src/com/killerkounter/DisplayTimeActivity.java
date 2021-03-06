package com.killerkounter;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;

public class DisplayTimeActivity extends ListActivity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_time);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		
		
		
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
		getMenuInflater().inflate(R.menu.display_time, menu);
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
			
			Intent intent = new Intent(this, MainActivity.class);
			NavUtils.navigateUpTo(this, intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/** 
	 * @uml.property name="displayStatisticsActivity"
	 * @uml.associationEnd inverse="displayTimeActivity:com.killerkounter.DisplayStatisticsActivity"
	 */
	private DisplayStatisticsActivity displayStatisticsActivity;

	/** 
	 * Getter of the property <tt>displayStatisticsActivity</tt>
	 * @return  Returns the displayStatisticsActivity.
	 * @uml.property  name="displayStatisticsActivity"
	 */
	public DisplayStatisticsActivity getDisplayStatisticsActivity() {
		return displayStatisticsActivity;
	}

	/** 
	 * Setter of the property <tt>displayStatisticsActivity</tt>
	 * @param displayStatisticsActivity  The displayStatisticsActivity to set.
	 * @uml.property  name="displayStatisticsActivity"
	 */
	public void setDisplayStatisticsActivity(
			DisplayStatisticsActivity displayStatisticsActivity) {
				this.displayStatisticsActivity = displayStatisticsActivity;
			}

}
