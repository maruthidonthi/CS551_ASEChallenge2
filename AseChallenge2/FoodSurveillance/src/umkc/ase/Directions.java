package umkc.ase;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
/**
 * @author jthd3
 *
 */
public class Directions extends SherlockFragment {
	 double latitudeE51 = 39.03497;
	 double longitudeE51 = -94.581023;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View imp_view = inflater.inflate(R.layout.map, container, false);
		Intent intent = new Intent(Intent.ACTION_VIEW,
				Uri.parse("geo:0,0?q=" + latitudeE51 + "," + longitudeE51
						+ "( Student Union)"));
		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(getActivity(),
					"No Map application found on the device!",
					Toast.LENGTH_SHORT).show();
		}

		return imp_view;
	}

}
