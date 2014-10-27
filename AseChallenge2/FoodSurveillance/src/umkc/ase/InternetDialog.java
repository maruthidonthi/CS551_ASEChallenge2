package umkc.ase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockDialogFragment;

/**
 * @author jthd3 Class for Notification on Internet Connection
 * 
 */
public class InternetDialog extends SherlockDialogFragment {

	@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.contact_dialog, null);
		Toast.makeText(getActivity(), "Internet Connection Needed",
				Toast.LENGTH_LONG).show();

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Bundle extras = this.getArguments();

	}

}
