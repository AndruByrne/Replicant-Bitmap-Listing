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
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.*;
import android.support.v4.view.GestureDetectorCompat;

public class DisplayTen extends Activity implements FlingListener, OnGestureListener {
//since we do not have any onClicks, it is fine to not use fragments as per instructions

	private boolean flinging = false;
	private static final String TAG = DisplayTen.class.getSimpleName( );
	private static final int TEN_ROWS = 10000;
	public GestureDetectorCompat gestureDetector; 
	private BitmapArrayAdapter bitmapArrayAdapter;
	private Scroller scroller;
	Handler handler;

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
		final Integer[] arrayDummy = new Integer[TEN_ROWS];

		handler = new Handler( );
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
    public boolean onDown( MotionEvent event ) {
//		Log.i( TAG, "onDowN!!!!!" );
//		flinging = false; //never, the asyc relies on being the only one to set this
        return true;
    }

	    private class StopSpinning extends AsyncTask<Void, Void, Boolean> {
		
		Scroller scroller;
		
		public StopSpinning( Scroller scroller ){
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
				new StopSpinning(scroller).execute( );

		}

	}

    @Override
    public boolean onFling( MotionEvent event1, MotionEvent event2, float velocityX, float velocityY ) {
		if( ( velocityY > 1000 || velocityX > 1000 ) && !flinging) {
			flinging = true;
			new StopSpinning(scroller).execute( );
		    //handler.postDelayed( waitForNoScroll, 750 );
		}
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
		MenuItem show10000 = menu.add( getString( R.string.show_100000 ) );
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
