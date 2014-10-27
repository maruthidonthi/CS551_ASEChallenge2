package umkc.ase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockDialogFragment;
/**
 * @author jthd3
 *
 */
public class HoursDialog extends SherlockDialogFragment{
	String name,mon,tues,wed,thur,fri,sat,sun;
	TextView monTime,tueTime,wedTime,thurTime,friTime,satTime,sunTime;
	
	public static SherlockDialogFragment newInstance(String title,String mon,String tues,String wed,String thur,String fri,String sat,String sun) {
		HoursDialog frag = new HoursDialog();
		Bundle args = new Bundle();
		args.putCharSequence("title", title);
		args.putCharSequence("mond", mon);
		args.putCharSequence("tuesd", tues);
		args.putCharSequence("wednes", wed);
		args.putCharSequence("thurd", thur);
		args.putCharSequence("frid", fri);
		args.putCharSequence("satd", sat);
		args.putCharSequence("sund", sun);
		
		frag.setArguments(args);
		return frag;
	}
	
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
		View view = inflater.inflate(R.layout.hours_dialog, null);
		
		Bundle b = getArguments();
		if (b != null) {
			name = getArguments().getString("title");
			mon = getArguments().getString("mond");
			tues = getArguments().getString("tuesd");
			wed = getArguments().getString("wednes");
			thur = getArguments().getString("thurd");
			fri = getArguments().getString("frid");
			sat = getArguments().getString("satd");
			sun = getArguments().getString("sund");
			
		}
		

		monTime=(TextView) view.findViewById(R.id.monday_time);
		tueTime=(TextView) view.findViewById(R.id.tuesday_time);
		wedTime=(TextView) view.findViewById(R.id.wednesday_time);
		thurTime=(TextView) view.findViewById(R.id.Thursday_time);
		friTime=(TextView) view.findViewById(R.id.Friday_time);
		satTime=(TextView) view.findViewById(R.id.Saturday_time);
		sunTime=(TextView) view.findViewById(R.id.Sunday_time);
		
		monTime.setText(mon);
		tueTime.setText(tues);
		wedTime.setText(wed);
		thurTime.setText(thur);
		friTime.setText(fri);
		satTime.setText(sat);
		sunTime.setText(sun);
		
		getDialog().setTitle(name);
		
		return view;
}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}
}
