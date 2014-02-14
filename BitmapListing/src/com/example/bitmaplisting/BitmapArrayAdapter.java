package com.example.bitmaplisting;
import android.widget.ArrayAdapter;
import android.content.*;
import android.view.*;
import android.widget.*;
import android.util.*;
import java.util.*;
import android.content.res.Resources;
import android.graphics.*;

public class BitmapArrayAdapter extends ArrayAdapter {
	private final Context context;
	private final Resources res;
	private final BitmapBundleProvider bitmapBundleProvider;

	private static final String TAG = BitmapArrayAdapter.class.getSimpleName();
	public BitmapArrayAdapter( Context context, Resources res, Integer[] arrayDummy ) {
		super( context, R.layout.row, arrayDummy);
		this.context = context;
		this.res = res;
		bitmapBundleProvider = new BitmapBundleProvider( res );
		Log.i(TAG, "constructed");
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent ) {
		View rowView;
		if( convertView == null){
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		rowView = layoutInflater.inflate( R.layout.row, parent, false );
		
		ViewHolder initHolder = new ViewHolder();
		initHolder.bitmap = (ImageView) rowView.findViewById( R.id.bitmap );
		initHolder.genTime = (TextView) rowView.findViewById( R.id.gen_time );
		initHolder.rowNum = (TextView) rowView.findViewById( R.id.row_num );
		
		rowView.setTag(initHolder);
		} else rowView = convertView;
		
		ViewHolder holder = (ViewHolder) rowView.getTag();
		BitmapBundle bitmapBundle = bitmapBundleProvider.getBitmapBundle();
		holder.bitmap.setImageBitmap( bitmapBundle.bitmap );
		holder.genTime.setText( res.getString(R.string.time_to_gen) + Integer.toString(bitmapBundle.delay) );
		holder.rowNum.setText( res.getString(R.string.row) + Integer.toString( position + 1 ) );		
        		
		return rowView;
	}

}
