package umkc.ase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * @author jthd3
 * 
 */
public class MainActivity extends BaseActivity implements
		ContactInfoFragment.OnContactSelectedListener,
		HoursFragment.OnHoursSelectedListener,
		LinksFragment.OnLinkSelectedListener {
	private Fragment mContent, mContent1;
	ActionBar actionbar;
	int backCount1 = 0;
	public static final String PREFS_NAME = "MyPrefsFile";


	public MainActivity() {
		super(R.string.app_name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onBackPressed() {
		int backCount = getSupportFragmentManager().getBackStackEntryCount();

		if (backCount1 > 1) {
			backCount1--;
			getSupportFragmentManager().popBackStack();

		} else if (backCount1 == 1)

		{
			if (((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE)
					| ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE)) {
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.details, mContent).commit();

				int slide = getSupportFragmentManager().beginTransaction()
						.replace(R.id.menu, new RandomList()).commit();
				backCount1 = 0;
			} else {
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.content_frame, mContent).commit();

				int slide = getSupportFragmentManager().beginTransaction()
						.replace(R.id.menu_frame, new RandomList()).commit();

				backCount1 = 0;
			}
		}

		else {
			// block where back has been pressed. since backstack is zero.

			new AlertDialog.Builder(this)
					.setIcon(R.drawable.alert)
					.setTitle("Food Surveillance")
					.setMessage(
							"Are you sure you want to exit the application?")
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									finish();
								}

							}).setNegativeButton("No", null).show();
		}

	}
	public void switchContent2(Fragment fragment) {
		if (((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE)
				| ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE)) {

			Fragment mContent1 = fragment;

			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.details, mContent1);
			transaction.addToBackStack(null);
			transaction.commit();
			backCount1++;
		} else {
			Fragment mContent1 = fragment;

			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.content_frame, mContent1);
			transaction.addToBackStack(null);
			transaction.commit();
			backCount1++;
			getSlidingMenu().showContent();
		}
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {

		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(
					savedInstanceState, "mContent");
		if (((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE)
				| ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE)) {

			if (mContent == null)
				mContent = new MainView1();

			setContentView(R.layout.content_frame);

			actionbar = getSupportActionBar();
			actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
			actionbar.setCustomView(R.layout.customlayout);

			getSupportFragmentManager().beginTransaction()
					.replace(R.id.details, mContent).commit();
			backCount1 = 0;

			int slide = getSupportFragmentManager().beginTransaction()
					.replace(R.id.menu, new RandomList()).commit();

			getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
			setSlidingActionBarEnabled(false);
			SlidingMenu sm = getSlidingMenu();
			sm.setSlidingEnabled(false);

			actionbar.setDisplayHomeAsUpEnabled(false);
			actionbar.setHomeButtonEnabled(false);
		} else {
			if (mContent == null)
				mContent = new MainView();

			setContentView(R.layout.content_frame);
			actionbar = getSupportActionBar();
			actionbar.setDisplayHomeAsUpEnabled(false);
			actionbar.setIcon(R.drawable.list_icon);
			actionbar.setBackgroundDrawable(new ColorDrawable(Color
					.parseColor("#007139")));
			// actionbar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);

			getSupportFragmentManager().beginTransaction()
					.replace(R.id.content_frame, mContent).commit();
			backCount1 = 0;

			setBehindContentView(R.layout.menu_frame);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.menu_frame, new RandomList()).commit();

			getSlidingMenu()
					.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
			setSlidingActionBarEnabled(true);
			getSlidingMenu().showMenu();
			
			
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			Boolean hasRun = settings.getBoolean("hasRun", false);
			if(!hasRun){
				
				
			}
		}
	}
	
	

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}

	public void switchContent(Fragment fragment) {
		if (((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE)
				| ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE)) {

			Fragment mContent1 = fragment;

			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.details, mContent1);
			transaction.addToBackStack(null);
			transaction.commit();
			backCount1 = 1;
		} else {
			Fragment mContent1 = fragment;

			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.content_frame, mContent1);
			transaction.addToBackStack(null);
			transaction.commit();
			backCount1 = 1;
			getSlidingMenu().showContent();
		}
	}

	

	public void switchContent1() {
		if (((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE)
				| ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE)) {

			backCount1 = 1;
		} else {
			System.out.println("Value in back count" + backCount1);
			if (backCount1 == 1) {
				backCount1 = 1;
				getSlidingMenu().showContent();
			} else if (backCount1 == 0) {
				backCount1 = 0;
				getSlidingMenu().showContent();
			}
		}
	}

	@Override
	public void OnContactSelected(String title, String phone, String email) {
		// TODO Auto-generated method stub
		ContactDialog newFrag1 = new ContactDialog();
		Bundle args = new Bundle();
		args.putCharSequence("title", title);
		args.putCharSequence("phone", phone);
		args.putCharSequence("email", email);

		newFrag1.setArguments(args);
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction t = fm.beginTransaction();
		ContactDialog dialog = (ContactDialog) fm
				.findFragmentByTag("directory");
		if (dialog != null)
			t.remove(dialog);
		newFrag1.show(t, "contact");

	}

	public void OnHoursSelected(String title, String mon, String tues,
			String wed, String thur, String fri, String sat, String sun) {
		HoursDialog frag1 = new HoursDialog();
		Bundle args = new Bundle();
		args.putCharSequence("title", title);
		args.putCharSequence("mond", mon);
		args.putCharSequence("tuesd", tues);
		args.putCharSequence("wednes", wed);
		args.putCharSequence("thurd", thur);
		args.putCharSequence("frid", fri);
		args.putCharSequence("satd", sat);
		args.putCharSequence("sund", sun);

		frag1.setArguments(args);

		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction t = fm.beginTransaction();
		getFragmentManager().popBackStack();
		HoursDialog dialog = (HoursDialog) fm.findFragmentByTag("directory");
		if (dialog != null)
			t.remove(dialog);
		frag1.show(t, "hours");

	}

	public void OnLinkSelected(int row) {
		WebFragment frag1 = new WebFragment();
		Bundle args = new Bundle();
		args.putInt("row", row);
		frag1.setArguments(args);

		// t.replace(R.id.content_frame, frag1);
		switchContent2(frag1);
		;

	}

	public void setActionBarTitle(String title) {
		actionbar.setTitle(title);
	}
	
	@Override
	public void onPostCreate(Bundle savedInstanceState) {
	   super.onPostCreate(savedInstanceState);
	   new Handler().postDelayed(new Runnable() {
	       @Override
	       public void run() {
	           
	    getSlidingMenu().showContent();
	       }
	   }, 2000);
	}

}
