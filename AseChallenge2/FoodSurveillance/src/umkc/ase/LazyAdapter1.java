package umkc.ase;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class LazyAdapter1 extends BaseAdapter
{
    
	 private Activity activity;
	    private List<String> data;
	    private static LayoutInflater inflater=null; 
	    
	    public LazyAdapter1(Activity a, List<String> d) 
	    {
	    	//super(a,d);
	        activity = a;
	        data=d;
	        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        
	    }
	    public View getView(int position, View convertView, ViewGroup parent) 
	    {
	        if(convertView==null)
	        	convertView = inflater.inflate(R.layout.list_row, null);
	            
	        TextView title = (TextView)convertView.findViewById(R.id.title); 
	        String text = data.get(position);
	        title.setText(text);
	        return convertView;
	    }
	    public int getCount(){return data.size();}
	    public String getItem(int position) {return data.get(position);}
	    public long getItemId(int position) {return position;}
	    
	    private void unhighlightCurrentRow(View rowView) {
	    	   rowView.setBackgroundColor(Color.TRANSPARENT);
	    	   
	    	}
}