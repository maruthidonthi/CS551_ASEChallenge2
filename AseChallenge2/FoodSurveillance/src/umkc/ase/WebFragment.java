package umkc.ase;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class WebFragment extends SherlockFragment {
	int rowindex, radioIndex;
	boolean isNetworkEnabled = false;
	public WebView webView;
	Context context;
	ProgressBar progressBar;

	public static Fragment newInstance(int rowValue) {
		WebFragment frag = new WebFragment();
		Bundle args = new Bundle();
		args.putInt("row", rowValue);
		frag.setArguments(args);
		return frag;
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.web_layout, null);

		Bundle b = getArguments();
		if (b != null) {
			rowindex = getArguments().getInt("row");
		}
		isNetworkEnabled = checkInternetConnection(getActivity());
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

		else {
			final ProgressDialog pd = ProgressDialog.show(getActivity(), "",
					"Loading.. Please Wait", true);

			webView = (WebView) view.findViewById(R.id.webViewFrag);

			webView.setWebViewClient(new MyWebViewClient() {
				public void onPageFinished(WebView view, String url) {
					pd.dismiss();
				}
			});

			webView.getSettings().setLoadWithOverviewMode(true);
			webView.getSettings().setUseWideViewPort(true);
			webView.getSettings().setJavaScriptEnabled(true);
			webView.getSettings().setRenderPriority(RenderPriority.HIGH);
			webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
			webView.getSettings().setAllowFileAccess(true);
			webView.getSettings().setBuiltInZoomControls(true);
			webView.getSettings().setDisplayZoomControls(false);
			webView.getSettings().setPluginsEnabled(true);
		

			switch (rowindex) {
			case 0:

				//webView.loadUrl("http://www.umkcbookstore.com/t-textbook-terms.aspx");
				Intent intent = new Intent(getActivity(),
						Pest.class);
				startActivity(intent);

				break;

			case 1:
				
				Intent intent1 = new Intent(getActivity(),
						Pest.class);
				startActivity(intent1);

				break;
			case 2:
				Intent intent3 = new Intent(getActivity(),
						Pest.class);
				startActivity(intent3);

				break;
			case 3:
				webView.loadUrl("https://roogroups.collegiatelink.net/events");

				break;
			case 4:
				webView.loadUrl("https://roogroups.collegiatelink.net/");

				break;
			case 5:
				webView.loadUrl("http://www.umkc.edu/union/reservation-request.asp");

				break;
			case 6:
				webView.loadUrl("http://www.umkc.edu/foodservice/specials.html");

				break;

			default:

				break;
			}
			
		}
		return view;

	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
	    outState.putString("test", "value");
	    super.onSaveInstanceState(outState);
	}

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	setRetainInstance(true);
	}

	class MyWebViewClient extends WebViewClient {
		@Override
		// Show in WebView instead of Browser
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
setRetainInstance(true);
	}

	public static boolean checkInternetConnection(Context context) {
		ConnectivityManager conMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo i = conMgr.getActiveNetworkInfo();
		if (i == null)
			return false;
		if (!i.isConnected())
			return false;
		if (!i.isAvailable())
			return false;
		return true;
	}
}
