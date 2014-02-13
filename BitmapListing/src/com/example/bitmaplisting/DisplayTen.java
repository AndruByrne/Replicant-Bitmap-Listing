package com.example.bitmaplisting;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;

public class DisplayTen extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display10);
    }

	@Override
	protected void onResume( ) {
		
		super.onResume( );
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		//return super.onCreateOptionsMenu( menu );
		MenuItem show100 = menu.add(getString(R.string.show_100 ) );
		show100.setShowAsAction( MenuItem.SHOW_AS_ACTION_NEVER );
		show100.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
			public boolean onMenuItemClick(MenuItem item ){
//				Intent intent = new Intent(DisplayTen.this, DisplayOneHundred.class);
//				DisplayTen.this.startActivity(intent);
				return false;
			}
		});
		MenuItem show1000 = menu.add(getString(R.string.show_1000 ) );
		show100.setShowAsAction( MenuItem.SHOW_AS_ACTION_NEVER );
		show100.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
				public boolean onMenuItemClick(MenuItem item ){
//					Intent intent = new Intent(DisplayTen.this, DisplayOneThousand.class);
//					DisplayTen.this.startActivity(intent);
					return false;
				}
			});
		return true;
	}
	
}
