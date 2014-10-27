package umkc.ase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
/**
 * @author jthd3
 * Class for Home Screen .
 *
 */
public class MainView extends SherlockFragment{
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);
	}
    @Override	
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {	
    	getActivity().setTitle(R.string.app_name);
	return inflater.inflate(R.layout.activity_main, container, false);
    }
    
	@Override
	public void onResume() {
	    super.onResume();
	    // Set title
	     getActivity().getActionBar()
	        .setTitle(R.string.app_name);
	}
    /*public void setText(String item) {
    TextView view = (TextView) getView().findViewById(R.id.fragtext1);
view.setText(item);
}*/
}
