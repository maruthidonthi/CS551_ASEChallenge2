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

public class MainAdapter extends BaseAdapter {


    private Activity activity;
    private List<String> data;
    private static LayoutInflater inflater=null; 
    
    public MainAdapter(Activity a, List<String> d) 
    {
    	//super(a,d);
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(int position, View convertView, ViewGroup parent) 
    {
    	ViewHolder holder;
    	if(convertView==null)
    	{
        	convertView = inflater.inflate(R.layout.listrow_main, null);
            
    	holder = new ViewHolder();
		holder.image = (ImageView) convertView.findViewById(R.id.arrow);
		holder.name = (TextView) convertView.findViewById(R.id.title);
		convertView.setTag(holder);
    	}
    	else {
			holder = (ViewHolder) convertView.getTag();
		}
        Typeface face=Typeface.createFromAsset(activity.getAssets(),
                "fonts/arial.ttf");

        holder.name.setTypeface(face);
        String text = data.get(position);
        holder.name.setText(text);
        switch(position)
        {
        case 0:
        	holder.image.setImageResource(R.drawable.map_lrg);
        	break;
        case 1:
        	holder.image.setImageResource(R.drawable.building_lrg);
        	break;
        case 2:
        	holder.image.setImageResource(R.drawable.phone_lrg);
        	break;
        case 3:
        	holder.image.setImageResource(R.drawable.like_lrg);
        	break;
        case 4:
        	holder.image.setImageResource(R.drawable.move_point_lrg);
        	break;
        case 5:
        	holder.image.setImageResource(R.drawable.like_lrg);
        	break;
        case 6:
        	holder.image.setImageResource(R.drawable.like_lrg);
        	break;
        case 7:
        	holder.image.setImageResource(R.drawable.like_lrg);
        	break;
        
        }
        return convertView;
    }
    public int getCount(){return data.size();}
    public String getItem(int position) {return data.get(position);}
    public long getItemId(int position) {return position;}
    
    static class ViewHolder {
		ImageView image;
		TextView name;
	}
}