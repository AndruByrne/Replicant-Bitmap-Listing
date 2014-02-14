package com.example.bitmaplisting;
import android.widget.ArrayAdapter;
import android.content.*;
import android.view.*;
import android.widget.*;
import android.util.*;
import java.util.*;
import android.content.res.Resources;

public class BitmapArrayAdapter extends ArrayAdapter<BitmapBundle> {
	private final Context context;
	private final List<BitmapBundle> bitmapBundles;
	private final Resources res;

	private static final String TAG = BitmapArrayAdapter.class.getSimpleName();
	public BitmapArrayAdapter( Context context, Resources res, List<BitmapBundle> bitmapBundles ) {
		super( context, R.layout.row, bitmapBundles );
		this.context = context;
		this.bitmapBundles = bitmapBundles;
		this.res = res;
		Log.i(TAG, "constructed");
	}

	@Override
	public View getView( int position, View convertView, ViewGroup parent ) {

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View rowView = layoutInflater.inflate( R.layout.row, parent, false );
		ImageView bitmap = (ImageView) rowView.findViewById( R.id.bitmap );
		TextView genTime = (TextView) rowView.findViewById( R.id.gen_time );
		TextView rowNum = (TextView) rowView.findViewById( R.id.row_num );

		Log.i(TAG, "getting View" );

		bitmap.setImageBitmap( bitmapBundles.get(position).bitmap );
		genTime.setText( res.getString(R.string.time_to_gen) + Integer.toString(bitmapBundles.get(position).delay) );
		rowNum.setText( res.getString(R.string.row) + Integer.toString( position ) );		

		return rowView;
	}

}
