package umkc.ase;

/**
 * @author jthd3
 *
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;

public class HoursFragment extends SherlockListFragment implements
		OnItemSelectedListener {

	public interface OnHoursSelectedListener {
		public void OnHoursSelected(String title, String mon, String tues,
				String wed, String thur, String fri, String sat, String sun);
	}

	String[] office;
	ListView listview;
	int j = 0, location;
	ProgressDialog pd;
	String locationName, json, building, time, parentName, url;
	boolean isNetworkEnabled = false;
	LinkedHashMap<String, String> jsonData = new LinkedHashMap<String, String>();
	OnHoursSelectedListener hours;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		List<String> name = new ArrayList<String>(Arrays.asList(office));
		setListAdapter(new LazyAdapter(getActivity(), name));
		listview = getListView();
		listview.setTextFilterEnabled(true);

		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parentView, View childView,
					int position, long id) {
				// TODO Auto-generated method stub
				pd = ProgressDialog.show(getActivity(), "",
						"Loading.. Please Wait", true);
				parentName = (String) parentView.getItemAtPosition(position);

				switch (position) {
				case 0:
					location = 32;
					break;
				case 1:
					location = 33;
					break;
				case 2:
					location = 34;
					break;
				case 3:
					location = 35;
					break;
				case 4:
					location = 36;
					break;
				case 5:
					location = 37;
					break;
				case 6:
					location = 38;
				default:
					break;
				}
				isNetworkEnabled = WebFragment
						.checkInternetConnection(getActivity());
				if (!isNetworkEnabled) {
					Toast.makeText(getActivity(), "Internet Connection Needed",
							Toast.LENGTH_LONG).show();
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
							getActivity());
					alertDialogBuilder.setTitle("Wi-Fi");
					alertDialogBuilder
							.setMessage(
									"Do you want to enable your wireless network?")
							.setCancelable(false)
							.setPositiveButton("Yes",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											startActivityForResult(
													new Intent(
															android.provider.Settings.ACTION_WIRELESS_SETTINGS),
													0);
										}
									})
							.setNegativeButton("No",
									new DialogInterface.OnClickListener() {
										public void onClick(
												DialogInterface dialog, int id) {
											dialog.cancel();
										}
									});
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
				} else {

					url = "http://php2.umkc.edu/intapps/location-manager/building-timings.php?building_id=22&location_id="
							+ location;

					try {
						json = new JsonAsyncTask().execute(url).get();

						JSONArray jsonArray = new JSONArray(json);
						int arrLen = jsonArray.length();
						for (int i = 0; i < arrLen; i++) {
							j = 0;
							JSONObject jsonObject = jsonArray.getJSONObject(i);
							building = jsonObject.getString("day");
							time = jsonObject.getString("time");
							jsonData.put(building, time);

						}
						String MondayTime = jsonData.get("Monday");
						String TuesdayTime = jsonData.get("Tuesday");
						String WednesdayTime = jsonData.get("Wednesday");
						String ThursdayTime = jsonData.get("Thursday");
						String FridayTime = jsonData.get("Friday");
						String SaturdayTime = jsonData.get("Saturday");
						String SundayTime = jsonData.get("Sunday");

						hours.OnHoursSelected(parentName, MondayTime,
								TuesdayTime, WednesdayTime, ThursdayTime,
								FridayTime, SaturdayTime, SundayTime);
					}

					catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				pd.dismiss();
			}

		});

	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);

		try {
			hours = (OnHoursSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ "must implement OnHoursSelectedListener");
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Resources res = getResources();
		office = res.getStringArray(R.array.BuildingHours);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View imp_view = inflater.inflate(R.layout.hours_layout, container,
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


	public void onBackPress() {

	}
}
