package umkc.ase;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChipsAdapter extends BaseAdapter {
	private Activity activity;
	private List<String> course;
	private LayoutInflater inflater=null;

	

	public ChipsAdapter(Activity activity1, List<String> course_list) {

		activity = activity1;
		course = course_list;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		ViewHolder holder;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.listcat, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.title);
			holder.image = (ImageView) convertView.findViewById(R.id.arrow1);
			holder.et = (EditText) convertView.findViewById(R.id.editText1);
			holder.qty = (TextView) convertView.findViewById(R.id.textView1);
		
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText(course.get(position));
		 switch(position)
	        {
	        case 0:
	        	holder.image.setImageResource(R.drawable.veg_meal);
	        	break;
	       
	       
	        
	        }
		 holder.image.setOnClickListener(new View.OnClickListener() {

			    @Override
			    public void onClick(View v) {
			        // TODO Auto-generated method stub
			       Toast.makeText(activity, "Item Selected"+"\n"+" Successfull Added To your Cart"+"\n"+"Amount Spent on item is: 5.03$", Toast.LENGTH_LONG).show();
			    }
			});

		 holder.et.addTextChangedListener(new TextWatcher(){

			 // String pos=course.get(position);

			    @Override
			    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			            int arg3) {
			        // TODO Auto-generated method stub

			    }

			    @Override
			    public void onTextChanged(CharSequence arg0, int arg1, int arg2,
			            int arg3) {
			        // TODO Auto-generated method stub

			    }

				@Override
				public void afterTextChanged(Editable s) {
					// TODO Auto-generated method stub
					  course.set(position, s.toString());
					
				}

			});
		
		
		
		
		return convertView;

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return course.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return course.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	static class ViewHolder {
		ImageView image;
		TextView name,qty;
		EditText et;
	}
}
