package com.example.bitmaplisting.activities;

import com.example.bitmaplisting.interfaces.FlingListener;
import com.example.bitmaplisting.adapters.BitmapArrayAdapter;
import com.example.bitmaplisting.activities.*;
import com.example.bitmaplisting.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.util.*;
import java.util.*;
import android.graphics.*;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.*;
import android.support.v4.view.GestureDetectorCompat;

public class DisplayHundred extends Activity implements FlingListener, OnGestureListener {
//since we do not have any onClicks, it is fine to not use fragments as per instructions

	private boolean flinging = false; //true if the list is scrolling
	private static final int rows = BitmapListing.HUNDRED_ROWS;
	public GestureDetectorCompat gestureDetector; //to catch flinging
	private BitmapArrayAdapter bitmapArrayAdapter; //holds the bitmapBundles
	private Scroller scroller; //tells if the list is scrolling

	@Override
	public boolean isFlinging( ) {
		if( flinging ) return true;
		else return false;
	}

    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.display10 );
		Resources res = getResources( );
		final ListView listView = (ListView) findViewById( R.id.list_view );
		final Integer[] arrayDummy = new Integer[rows];

		scroller = new Scroller( this );
		bitmapArrayAdapter = new BitmapArrayAdapter( this, this, res, arrayDummy );
		gestureDetector = new GestureDetectorCompat( this, this );
		listView.setOnTouchListener( new OnTouchListener( ){
				public boolean onTouch( View view, MotionEvent event ) {
					gestureDetector.onTouchEvent( event );
					return false;
				}
			} );
		listView.setAdapter( bitmapArrayAdapter );
    }

	//for the flinging

    @Override
    public boolean onFling( MotionEvent event1, MotionEvent event2, float velocityX, float velocityY ) {
		if( ( velocityY > 1000 || velocityX > 1000 ) && !flinging ) {
			flinging = true;
			new StopSpinning( scroller ).execute( );
		    //handler.postDelayed( waitForNoScroll, 750 );
		}
        return true;
    }

//can't think of a better name, but this checks to see when the spinning stops
//and then releases the lock on the ArrayAdapter and tells it to refresh
	private class StopSpinning extends AsyncTask<Void, Void, Boolean> {

		Scroller scroller;

		public StopSpinning( Scroller scroller ) {
			this.scroller = scroller;
		}

		@Override
		protected Boolean doInBackground( Void[] p1 ) {
			try {
				Thread.sleep( 75 );
			} catch(InterruptedException e) {}
			return scroller.isFinished( );
		}

		@Override
		protected void onPostExecute( Boolean finished ) {
			super.onPostExecute( finished );
			if( finished ) {
				flinging = false;
				bitmapArrayAdapter.notifyDataSetChanged( );
			} else if( flinging )
				new StopSpinning( scroller ).execute( );

		}

	}

    @Override
    public boolean onDown( MotionEvent event ) {
//		Log.i( TAG, "onDowN!!!!!" );
//		flinging = false; //never, the asyc relies on being the only one to set this
        return true;
    }

    @Override
    public void onLongPress( MotionEvent event ) {
    }

    @Override
    public boolean onScroll( MotionEvent event1, MotionEvent event2, float distanceX, float distanceY ) {
        return true;
    }

    @Override
    public void onShowPress( MotionEvent event ) {
    }    

    @Override
    public boolean onSingleTapUp( MotionEvent event ) {
        return true;
    }

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		MenuItem show10 = menu.add( getString( R.string.show_10 ) );
		show10.setShowAsAction( MenuItem.SHOW_AS_ACTION_NEVER );
		show10.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener( ){
				public boolean onMenuItemClick( MenuItem item ) {
				Intent intent = new Intent(DisplayHundred.this, DisplayTen.class);
				DisplayHundred.this.startActivity(intent);
					return false;
				}
			} );
		MenuItem show1000 = menu.add( getString( R.string.show_1000 ) );
		show1000.setShowAsAction( MenuItem.SHOW_AS_ACTION_NEVER );
		show1000.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener( ){
				public boolean onMenuItemClick( MenuItem item ) {
					Intent intent = new Intent(DisplayHundred.this, DisplayThousand.class);
					DisplayHundred.this.startActivity(intent);
					return false;
				}
			} );
		MenuItem show100000 = menu.add( getString( R.string.show_100000 ) );
		show100000.setShowAsAction( MenuItem.SHOW_AS_ACTION_NEVER );
		show100000.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener( ){
				public boolean onMenuItemClick( MenuItem item ) {
					Intent intent = new Intent(DisplayHundred.this, DisplayHundredThousand.class);
					DisplayHundred.this.startActivity(intent);
					return false;
				}
			} );
		return true;
	}

	@Override
	public void onBackPressed( ) {
		super.onBackPressed( );
		moveTaskToBack( true );
	}

}
