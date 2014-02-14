package com.example.bitmaplisting;
import android.graphics.*;
import android.content.res.*;
import java.util.*;
import android.util.*;

public class BitmapBundleProvider {
	private static final String TAG = BitmapBundleProvider.class.getSimpleName( );
	private BitmapBundle bitmapBundle; //bitmap and delay
	private Resources res;

	private Random r;
	private int maxRnd = 500;

	public BitmapBundleProvider( Resources res ) {
		this.res=res;
	}

	public BitmapBundle getBitmapBundle( ) {
		bitmapBundle = new BitmapBundle();
		bitmapBundle.bitmap = BitmapFactory.decodeResource( res, R.drawable.thebitmap);
				
		r = new Random();		
		int delay = r.nextInt( maxRnd + 1 );
		
		try {
			Thread.sleep( delay );
			bitmapBundle.delay = delay;
		} catch(InterruptedException e) {
			Log.e( TAG, "Stopped waiting: " + e.toString( ) ); 
		    bitmapBundle.delay = 0;
		}
		return bitmapBundle;
	}
}
