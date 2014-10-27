package umkc.ase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends Activity {
	
	EditText cardno, username,cardtype,emailid;
	Button login;
	String user,type,no,email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.payment);

		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.activity_login);
		
		TextView title = (TextView) findViewById(R.id.title);
		title.setText("Payment Process");
		
		final Context context = this;
		

		username = (EditText) findViewById(R.id.editText1);
		cardno = (EditText) findViewById(R.id.editText2);
		cardtype = (EditText) findViewById(R.id.editText3);
		emailid = (EditText) findViewById(R.id.editText4);
		
		
		
		login = (Button)findViewById(R.id.button1);
		login.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				user = username.getText().toString();
				no = cardno.getText().toString();
				type = cardtype.getText().toString();
				email = emailid.getText().toString();
				
				

				System.out.println("the values of the login are "+user+" "+no);
				if((user!=null)&& (no!=null) && (type!=null)&& (email!=null))
				{
							
					System.out.println("I am in the login");
					Intent intent = new Intent(getApplicationContext(),
							Webview.class);
					startActivity(intent);
				}
				else
				{
					username.setText("");
					cardno.setText("");
					cardtype.setText("");
					emailid.setText("");
					
					Toast.makeText(getBaseContext(), "Please enter all details", Toast.LENGTH_SHORT).show();
				}
			}
			
			   
		});
		
	}
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





	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

	
	


