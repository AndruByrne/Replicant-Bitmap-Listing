package com.example.bitmaplisting;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.util.*;
import java.util.*;
import android.graphics.*;

public class DisplayTen extends Activity {

	private static final String TAG = DisplayTen.class.getSimpleName();
	//since we do not have any onClicks, it is fine to not use fragments
	private static final int TEN_ROWS = 10;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.display10 );
		Resources res = getResources( );
		final ListView listView = (ListView) findViewById( R.id.list_view );
		final BitmapBundleProvider bitmapBundleProvider = new BitmapBundleProvider( res );
		final ArrayList <BitmapBundle> bitmapArrayList= new ArrayList<BitmapBundle>();
		for( int i = 0; i < TEN_ROWS; ++i){
			bitmapArrayList.add(bitmapBundleProvider.getBitmapBundle());
		}
		final BitmapArrayAdapter bitmapArrayAdapter = new BitmapArrayAdapter(this, res, bitmapArrayList);
		listView.setAdapter(bitmapArrayAdapter);
    }

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		//return super.onCreateOptionsMenu( menu );
		MenuItem show100 = menu.add( getString( R.string.show_100 ) );
		show100.setShowAsAction( MenuItem.SHOW_AS_ACTION_NEVER );
		show100.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener( ){
				public boolean onMenuItemClick( MenuItem item ) {
//				Intent intent = new Intent(DisplayTen.this, DisplayOneHundred.class);
//				DisplayTen.this.startActivity(intent);
					return false;
				}
			} );
		MenuItem show1000 = menu.add( getString( R.string.show_1000 ) );
		show100.setShowAsAction( MenuItem.SHOW_AS_ACTION_NEVER );
		show100.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener( ){
				public boolean onMenuItemClick( MenuItem item ) {
//					Intent intent = new Intent(DisplayTen.this, DisplayOneThousand.class);
//					DisplayTen.this.startActivity(intent);
					return false;
				}
			} );
		return true;
	}

}
