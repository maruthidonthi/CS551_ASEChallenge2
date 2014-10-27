package umkc.ase;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
/**
 * @author jthd3
 *
 */

public class BaseActivity extends SlidingFragmentActivity{
	private int mTitleRes;
	protected ListFragment mFrag;
	final String PREFS_NAME = "MyPrefsFile";
	Context context;
	SharedPreferences settings;
	
	
	public BaseActivity(int titleRes)
	{
		mTitleRes = titleRes;
	}

	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle(mTitleRes);
		
		setBehindContentView(R.layout.menu_frame);
		FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
		mFrag = new RandomList();
		
		ft.replace(R.id.menu_frame, mFrag);
		ft.commit();
		
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		
		
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);
		
 settings =getSharedPreferences(PREFS_NAME,0);
		
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId())
		{
		case android.R.id.home:
			toggle();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getSupportMenuInflater().inflate(R.menu.activity_menu, menu);
		if (((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE)
				| ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE)) {
		}
		else
		{
			
		

		if (settings.getBoolean("my_first_time", true)) {
	toggle();
	  settings.edit().putBoolean("my_first_time", false).commit(); 
		}
		}
		return true;
	}
	public class BasePagerAdapter extends FragmentPagerAdapter{

		private List<Fragment> mFragments = new ArrayList<Fragment>();
		private ViewPager mPager;
		public BasePagerAdapter(FragmentManager fm, ViewPager vp) {
			super(fm);
			mPager = vp;
			mPager.setAdapter(this);
			for(int i = 0;i < 3; i++)
			{
				addTab(new RandomList());
			}
			// TODO Auto-generated constructor stub
		}

		public void addTab(Fragment frag) {
			// TODO Auto-generated method stub
			mFragments.add(frag);
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			return mFragments.get(position);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mFragments.size();
		}
		
	}

}
