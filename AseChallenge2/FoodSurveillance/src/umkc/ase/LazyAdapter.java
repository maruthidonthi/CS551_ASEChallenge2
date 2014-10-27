package umkc.ase;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * @author jthd3
 * Adapter to load the list views in ContactInfoFragment and HoursFragment.
 *
 */
public class LazyAdapter extends BaseAdapter {

	private Activity activity;
	private List<String> data;
	private static LayoutInflater inflater = null;

	public LazyAdapter(Activity a, List<String> d) {
		// super(a,d);
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list_row1, null);

			holder = new ViewHolder();
			holder.image = (ImageView) convertView.findViewById(R.id.arrow1);
			holder.name = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Typeface face = Typeface.createFromAsset(activity.getAssets(),
				"fonts/arial.ttf");

		holder.name.setText((CharSequence) data.get(position));
		holder.image.setImageResource(R.drawable.arrow_black);
		return convertView;
	}

	public int getCount() {
		return data.size();
	}

	public String getItem(int position) {
		return data.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {
		ImageView image;
		TextView name;
	}
}