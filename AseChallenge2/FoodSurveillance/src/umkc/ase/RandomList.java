package umkc.ase;

import java.util.ArrayList;
import java.util.List;

import umkc.ase.LinksFragment.OnLinkSelectedListener;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.internal.widget.IcsAdapterView;
import com.actionbarsherlock.internal.widget.IcsAdapterView.OnItemSelectedListener;

/**
 * @author jthd3
 * Class for Main List .
 *
 */
public class RandomList extends SherlockListFragment implements
		LinksFragment.OnLinkSelectedListener, OnItemSelectedListener {
	public ListView lv;
	public Fragment f;
	public MainActivity m;
	OnLinkSelectedListener radio;
	private double latitudeE51 = 51.5033630;
	private double longitudeE51 =-0.1276250;
	boolean isNetworkEnabled= false;
	private View currentSelectedView;
	private Boolean firstTimeStartup = true;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		List<String> items = new ArrayList<String>();
		//items.add("Home");
		items.add("Get Directions");
		items.add("Food supply");
		
		items.add("Contact US");
		items.add("Feedback");
		items.add("Make Donation");
items.add("Find nearby places");
		setListAdapter(new MainAdapter(getActivity(), items));
		lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parentView, View childView,
					int position, long id) {
				if (firstTimeStartup) {// first time  highlight first row
					currentSelectedView = lv.getChildAt(0);
					}
					firstTimeStartup = false; 
					if (currentSelectedView != null && currentSelectedView != childView) {
					       unhighlightCurrentRow(currentSelectedView);
					   }
					 
					currentSelectedView = childView;
					   highlightCurrentRow(currentSelectedView);
				getNextPage(position);
			}

		});
	}
	 public void disableItemSelection() {
	        ListView lv = getListView();
	        for (int i = 0; i < lv.getChildCount(); i++){
	            View v = lv.getChildAt(i);
	            v.setEnabled(false);
	        }
	    }
	private void unhighlightCurrentRow(View rowView) {
		   rowView.setBackgroundColor(Color.TRANSPARENT);
		   
		}

		private void highlightCurrentRow(View rowView) {
		   rowView.setBackgroundColor(getResources().getColor(
		         R.color.focus));
		   

		} 
	public void getNextPage(int index) {
		Context context = getActivity();
		String item = (String) getListAdapter().getItem(index);
		if (index == 2) {
			f = new ContactInfoFragment();
			m = (MainActivity) getActivity();
			m.switchContent(f);
		} else if (index == 0) {
			Intent intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("geo:0,0?q=" + (latitudeE51+","+ longitudeE51)));

			try {
				startActivity(intent);
			} catch (ActivityNotFoundException e) {
				Toast.makeText(context,
						"No Map application found on the device!",
						Toast.LENGTH_SHORT).show();
			}
			m = (MainActivity) getActivity();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			m.switchContent1();
		} else if (index == 2) {
			f = new Radio();
			m = (MainActivity) getActivity();
			m.switchContent(f);
		}
		else if (index == 4) {
			f = new Radio();
			
			m = (MainActivity) getActivity();
			m.switchContent(f);
		} else if (index == 1) {
			f = new LinksFragment();
			m = (MainActivity) getActivity();
			m.switchContent(f);
		} else if (index == 3) {
			isNetworkEnabled = WebFragment.checkInternetConnection(getActivity());
			if (!isNetworkEnabled) {
				Toast.makeText(getActivity(), "Internet Connection Needed",
						Toast.LENGTH_LONG).show();
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						getActivity());
				alertDialogBuilder.setTitle("Wi-Fi");
				alertDialogBuilder
						.setMessage("Do you want to enable your wireless network?")
						.setCancelable(false)
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										startActivityForResult(
												new Intent(
														Settings.ACTION_WIFI_SETTINGS),
												0);
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int id) {
										dialog.cancel();
									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
			}
			else{
			final Intent _Intent = new Intent(
					android.content.Intent.ACTION_SEND);
			_Intent.setType("message/rfc822");
			_Intent.putExtra(android.content.Intent.EXTRA_EMAIL,
					new String[] { getString(R.string.mail_feedback_email) });
			_Intent.putExtra(android.content.Intent.EXTRA_SUBJECT,
					getString(R.string.mail_feedback_subject));
			startActivity(Intent.createChooser(_Intent,
					getString(R.string.title_send_feedback)));
			m = (MainActivity) getActivity();
			m.switchContent1();
		}
		}

	}

	@Override
	public void OnLinkSelected(int rowIndex) {
		// TODO Auto-generated method stub
		WebFragment frag1 = new WebFragment();
		Bundle args = new Bundle();
		args.putInt("row", rowIndex);
		frag1.setArguments(args);

		FragmentManager fm = getFragmentManager();
		FragmentTransaction t = fm.beginTransaction();
		t.replace(R.id.content_frame, frag1);
		t.commit();

	}

	@Override
	public void onItemSelected(IcsAdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onNothingSelected(IcsAdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        onBackPressed();
	        return true;
	    }
	    return false;
	}

	private void onBackPressed() {
		Fragment frag1 = new Fragment();
		FragmentManager fm = getFragmentManager();
		FragmentTransaction t = fm.beginTransaction();
		t.replace(R.id.content_frame, frag1);
		t.commit(); 
	}

	

}
