package com.example.bitmaplisting;
import android.graphics.*;
import android.content.res.*;
import java.util.*;
import android.util.*;

public class BitmapProvider
{
	private static final String TAG = BitmapProvider.class.getSimpleName();
	private BitmapFactory bitmapFactory;
	private BitmapBundle bitmapBundle; //bitmap and delay

	private Random r;
	private int maxRnd = 500;
	private int delay;

	public BitmapProvider(){
		bitmapFactory = new BitmapFactory();
		bitmapBundle.bitmap = bitmapFactory.decodeResource(android.content.res.Resources.getSystem(), R.drawable.thebitmap);
	}
	
	public BitmapBundle getBitmap(){
		delay = r.nextInt(maxRnd+1);
		try {
			wait( delay );
		} catch(InterruptedException e) {Log.e(TAG, "Stopped waiting: "+e.toString());}
		bitmapBundle.delay = delay;
		return bitmapBundle;
	}
}
