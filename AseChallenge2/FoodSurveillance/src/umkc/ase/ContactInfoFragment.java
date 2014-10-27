package umkc.ase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockListFragment;
/**
 * @author jthd3
 *
 */
public class ContactInfoFragment extends SherlockListFragment implements
		OnItemSelectedListener {

	public interface OnContactSelectedListener {
		public void OnContactSelected(String title, String phone, String email);
	}

	String[] contact, val, phonenum;
	ListView lv, listview;
	boolean CallAbility = false;
	OnContactSelectedListener contacts;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setRetainInstance(true);
		List<String> name = new ArrayList<String>(Arrays.asList(val));
		List<String> phone = new ArrayList<String>(Arrays.asList(phonenum));

		SharedPreferences sp = PreferenceManager
				.getDefaultSharedPreferences(getActivity());
		Map<String, ?> map = sp.getAll();
		Set<String> str = map.keySet();

		for (String s : str) {
			name.add(s);
			phone.add((String) map.get(s));
		}

		final List<String> phone_updated = phone;
		final List<String> name_updated = name;
		setListAdapter(new LazyAdapter(getActivity(), name));
		listview = getListView();
		listview.setTextFilterEnabled(true);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String title = null, phone = null, email = null;
				switch (position) {

				case 0:
					title = (String) parent.getItemAtPosition(position);
					phone = phone_updated.get(position);
					email = "jthd3@mail.umkc.edu";
					break;
				case 1:
					title = (String) parent.getItemAtPosition(position);
					phone = phone_updated.get(position);
					email = "";
					break;
				case 2:
					title = (String) parent.getItemAtPosition(position);
					phone = phone_updated.get(position);
					email = "umkconecard@umkc.edu";
					break;
				case 3:
					title = (String) parent.getItemAtPosition(position);
					phone = phone_updated.get(position);
					email = "getinvolved@umkc.edu";
					break;
				case 4:
					title = (String) parent.getItemAtPosition(position);
					phone = phone_updated.get(position);
					email = "umkcbookstore@umkc.edu";
					break;
				case 5:
					title = (String) parent.getItemAtPosition(position);
					phone = phone_updated.get(position);
					email = "lamasterp@umkc.edu";
					break;
				case 6:
					title = (String) parent.getItemAtPosition(position);
					phone = phone_updated.get(position);
					email = "jthd3@mail.umkc.edu";
					break;
				case 7:
					title = (String) parent.getItemAtPosition(position);
					phone = phone_updated.get(position);
					email = "jatinder.singh@sodexo.com";
					break;
				case 8:
					title = (String) parent.getItemAtPosition(position);
					phone = phone_updated.get(position);
					email = "umkcshared@usbank.com";
					default:
						break;
				}
				contacts.OnContactSelected(title, phone, email);

			}
		});
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Resources res = getResources();
		val = res.getStringArray(R.array.Contact_Office);
		phonenum = res.getStringArray(R.array.Phones);

		setRetainInstance(true);

	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub

		super.onAttach(activity);

		try {

			contacts = (OnContactSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ "must implement OnContactSelectedListener");
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View imp_view = inflater.inflate(R.layout.contact_layout, container,
				false);
		getActivity().setTitle(R.string.Hours);
		return imp_view;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}


}
