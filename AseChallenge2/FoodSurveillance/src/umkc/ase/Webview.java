package umkc.ase;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.view.Menu;
import android.webkit.WebView;

public class Webview extends Activity {
	
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		
		webView = (WebView) findViewById(R.id.webView1);
		webView.getSettings().setJavaScriptEnabled(true);
		//webView.loadUrl("http://KC-SCE-CS551.kc.umkc.edu/aspnet_client/CS551SoapService/WebService.asmx");
		webView.loadUrl("file:///android_asset/Donthi_mp4.html");
	}
	
	
	@Override
	public void onBackPressed() {
		

		
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
