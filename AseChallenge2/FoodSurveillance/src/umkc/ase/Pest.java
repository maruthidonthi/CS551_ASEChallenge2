package umkc.ase;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Pest extends ListActivity {

	List<String> menu_list = new ArrayList<String>();
	ListView lv;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.menu_list);

		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
			R.layout.app_title_bar);

		TextView title = (TextView) findViewById(R.id.title);
		title.setText("Welcome  User");

		menu_list.add("Bugs"+"\n"+"Cost: 6.00$"+"\n"+"Available");
		//menu_list.add("Pets"+"\n"+"Cost: 7.00$"+"\n"+"Available");

		if (menu_list != null) {

			setListAdapter(new PestListAdapter(this, menu_list));
			lv = getListView();
			lv.setTextFilterEnabled(true);

			lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parentView,
						View childView, int position, long id) {
					if (position == 0) {
						Intent intent = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
					} else if (position == 1) {
						Intent intent = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
					} else {
						Toast.makeText(getApplicationContext(),
								"Select either Friends or About us options",
								Toast.LENGTH_LONG).show();
					}
				}
			});
			
			Button Logout = (Button)findViewById(R.id.logout);
			Logout.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
				}
				
			});
		}
	}
}
