package umkc.ase;

import java.lang.reflect.InvocationTargetException;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class Radio extends Fragment{
	public WebView webView;
	ProgressBar progressBar;
	boolean isNetworkEnabled = false;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	   
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.web_view, container,false);
		getActivity().setTitle(R.string.Radio);
		
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
		
		final ProgressDialog pd = ProgressDialog.show(getActivity(), "",
				"Loading.. Please Wait", true);

		webView = (WebView) view.findViewById(R.id.webViewFrag1);
		Intent intent = new Intent(getActivity(),
				Payment.class);
		startActivity(intent);

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
		//webView.loadUrl("http://k-roo.org/");
		}
		return view;
		
	
	}
	public void onBackPressed() {
	onPause();
	return;
	}
	
	@Override
	public void onPause() {
	    super.onPause();

	    try {
	        Class.forName("android.webkit.WebView")
	                .getMethod("onPause", (Class[]) null)
	                            .invoke(webView, (Object[]) null);
	        webView.loadUrl("file:///android_asset/nonexistent.html");

	    } catch(ClassNotFoundException cnfe) {
	       cnfe.printStackTrace();
	    } catch(NoSuchMethodException nsme) {
	       nsme.printStackTrace();
	    } catch (IllegalAccessException iae) {
	       iae.printStackTrace();
	    } catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class MyWebViewClient extends WebViewClient 
	{
	    @Override
	    // Show in WebView instead of Browser
	    public boolean shouldOverrideUrlLoading(WebView view, String url)
	    {
	        view.loadUrl(url);
	        return true;
	    }
	}

}
