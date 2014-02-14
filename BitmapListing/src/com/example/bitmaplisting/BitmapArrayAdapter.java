package com.example.bitmaplisting;
import android.widget.ArrayAdapter;
import android.content.*;
import android.view.*;
import android.widget.*;
import android.util.*;
import java.util.*;
import android.content.res.Resources;
import android.graphics.*;
import android.os.*;

public class BitmapArrayAdapter extends ArrayAdapter {
	private final Context context;
	private final Resources res;
	public static final String TAG = BitmapArrayAdapter.class.getSimpleName( );
	private FlingListener flingListener;
	
	public BitmapArrayAdapter( Context context, FlingListener listener, Resources res, Integer[] arrayDummy ) {
		super( context, R.layout.row, arrayDummy );
		flingListener = listener;
		this.context = context;
		this.res = res;
	}
	
	@Override
	public View getView( int position, View convertView, ViewGroup parent ) {
		View rowView;
		if( convertView == null ) {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			rowView = layoutInflater.inflate( R.layout.row, parent, false );

			ViewHolder initHolder = new ViewHolder( );
			initHolder.bitmap = (ImageView) rowView.findViewById( R.id.bitmap );
			initHolder.genTime = (TextView) rowView.findViewById( R.id.gen_time );
			initHolder.rowNum = (TextView) rowView.findViewById( R.id.row_num );

			rowView.setTag( initHolder );
			} else rowView = convertView;

		ViewHolder holder = (ViewHolder) rowView.getTag( );
		holder.position = position;

		//async task

		if( !flingListener.isFlinging() )
		    new GetBitmapBundle( position, holder, res ).execute( );

		return rowView;
	}

	private static class GetBitmapBundle extends AsyncTask<Void, Void, BitmapBundle> {

		private static final int maxRnd = 500;
		private int position;
		private ViewHolder asyncHolder;
		private Resources res;

		public GetBitmapBundle( int position, ViewHolder asyncHolder, Resources res ) {
			this.position = position;
			this.asyncHolder = asyncHolder;
			this.res = res;
		}

		@Override
		protected BitmapBundle doInBackground( Void...v ) {
			BitmapBundle bitmapBundle = new BitmapBundle( );
			bitmapBundle.bitmap = BitmapFactory.decodeResource( res, R.drawable.thebitmap );

			Random r = new Random( );		
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

		@Override
		protected void onPostExecute( BitmapBundle bitmapBundle ) {
			super.onPostExecute( bitmapBundle );

			if( asyncHolder.position == position) {
				asyncHolder.bitmap.setImageBitmap( bitmapBundle.bitmap );
				asyncHolder.genTime.setText( res.getString( R.string.time_to_gen ) + Integer.toString( bitmapBundle.delay ) );
				asyncHolder.rowNum.setText( res.getString( R.string.row ) + Integer.toString( asyncHolder.position + 1 ) );
			}
		}
	} 

}
