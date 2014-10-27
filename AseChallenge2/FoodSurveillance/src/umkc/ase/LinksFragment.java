package umkc.ase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.SimpleExpandableListAdapter;

import com.actionbarsherlock.app.SherlockFragment;

public class LinksFragment extends SherlockFragment implements
		ExpandableListView.OnChildClickListener, OnItemSelectedListener{

	public interface OnLinkSelectedListener {
		public void OnLinkSelected(int rowIndex);
	}

	private static final String NAME = "NAME";
	String[] parentGroup;
	ExpandableListAdapter mAdapter;
	OnLinkSelectedListener link;
	int rowIndex;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
		List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
		parentGroup = getResources().getStringArray(R.array.ParentGroup);

		for (int i = 0; i < parentGroup.length; i++) {
			Map<String, String> curGroupMap = new HashMap<String, String>();
			groupData.add(curGroupMap);
			curGroupMap.put(NAME, parentGroup[i]);

			List<Map<String, String>> children = new ArrayList<Map<String, String>>();

			switch (i) {
			case 0:
				String[] one = getResources().getStringArray(R.array.bookstore);
				for (int j = 0; j < one.length; j++) {
					Map<String, String> curChildMap = new HashMap<String, String>();
					children.add(curChildMap);
					curChildMap.put(NAME, one[j]);
				}
				break;
			case 1:
				String[] two = getResources().getStringArray(R.array.Events);
				for (int j = 0; j < two.length; j++) {
					Map<String, String> curChildMap = new HashMap<String, String>();
					children.add(curChildMap);
					curChildMap.put(NAME, two[j]);
				}
				break;
			case 2:
				String[] three = getResources().getStringArray(R.array.Reserve);
				for (int j = 0; j < three.length; j++) {
					Map<String, String> curChildMap = new HashMap<String, String>();
					children.add(curChildMap);
					curChildMap.put(NAME, three[j]);
				}
				break;
			case 3:
				String[] four = getResources().getStringArray(R.array.Dining);
				for (int j = 0; j < four.length; j++) {
					Map<String, String> curChildMap = new HashMap<String, String>();
					children.add(curChildMap);
					curChildMap.put(NAME, four[j]);

				}
				break;
			}
			childData.add(children);
		}
		mAdapter = new SimpleExpandableListAdapter(getActivity(), groupData,
				R.layout.group_row, new String[] { NAME },
				new int[] { R.id.row_name }, childData, R.layout.child_row,
				new String[] { NAME }, new int[] { R.id.grp_child });
		ExpandableListView elv = (ExpandableListView) getActivity()
				.findViewById(R.id.explv);
		elv.setAdapter(mAdapter);
		elv.setOnChildClickListener(new OnChildClickListener() {
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				switch (groupPosition) {
				case 0:
					getBookstoreView(childPosition);
					break;
				case 1:
					getEventsView(childPosition);
					break;
				case 2:
					getReserveView(childPosition);
					break;
				case 3:
					getDiningView(childPosition);
					break;

				default:
					break;
				}
				return true;
			}

		}

		);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setRetainInstance(true);

	}

	public void getBookstoreView(int childPosition) {
		// TODO Auto-generated method stub

		switch (childPosition) {
		case 0:
			rowIndex = 0;
			Intent intent = new Intent(getActivity(),
					Bathroom.class);
			startActivity(intent);
			//link.OnLinkSelected(rowIndex);
			break;
		case 1:
			rowIndex = 1;
			Intent intent1 = new Intent(getActivity(),
					Pest.class);
			startActivity(intent1);
			//link.OnLinkSelected(rowIndex);
			break;
		case 2:
			rowIndex = 2;
			
			//link.OnLinkSelected(rowIndex);
			break;
		default:
			break;
		}
	}

	private void getEventsView(int childPosition) {
		// TODO Auto-generated method stub
		switch (childPosition) {
		case 0:
			rowIndex = 3;
			Intent intent = new Intent(getActivity(),
					Chips.class);
			startActivity(intent);
			//link.OnLinkSelected(rowIndex);
			break;
			
		case 1:
			rowIndex = 3;
			Intent intent2 = new Intent(getActivity(),
					Cookies.class);
			startActivity(intent2);
		//	link.OnLinkSelected(rowIndex);
			break;

		default:
			Intent intent1 = new Intent(getActivity(),
					Juices.class);
			startActivity(intent1);
			break;
		}
	}

	private void getReserveView(int childPosition) {
		// TODO Auto-generated method stub

		switch (childPosition) {
		case 0:
			rowIndex = 4;
			Intent intent1 = new Intent(getActivity(),
					Juices.class);
			startActivity(intent1);
			//link.OnLinkSelected(rowIndex);
			break;
		case 1:
			rowIndex = 5;
			Intent intent= new Intent(getActivity(),
					Coffee.class);
			startActivity(intent);
			
			//link.OnLinkSelected(rowIndex);
			break;
		default:
			break;
		}
	}

	private void getDiningView(int childPosition) {
		// TODO Auto-generated method stub
		switch (childPosition) {
		case 0:
			rowIndex = 6;
			Intent intent1 = new Intent(getActivity(),
					Raw.class);
			startActivity(intent1);
			//link.OnLinkSelected(rowIndex);
			break;

		default:
			Intent intent= new Intent(getActivity(),
					Salt.class);
			startActivity(intent);
			break;
		}
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);

		try {
			link = (OnLinkSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ "must implement OnLinkSelectedListener");
		}
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View imp_view = inflater.inflate(R.layout.links_layout, container,
				false);
getActivity().setTitle(R.string.Links);
		return imp_view;
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		return false;
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
