package umkc.ase;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockDialogFragment;
/**
 * @author jthd3
 * Class for Contact Info Dialog .
 *
 */
public class ContactDialog extends SherlockDialogFragment {
	boolean CallAbility = false;
	String title1, phone1, email1;
	TextView phone, email12,email_id;
	Button call, add, emailid;

	public static SherlockDialogFragment newInstance(String title,
			String phone, String email) {
		ContactDialog frag = new ContactDialog();
		Bundle args = new Bundle();
		args.putCharSequence("title", title);
		args.putCharSequence("phone", phone);
		args.putCharSequence("email", email);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public void onActivityCreated(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onActivityCreated(arg0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.contact_dialog, null);
		final PackageManager pm = getActivity().getPackageManager();
		Bundle b = getArguments();
		if (b != null) {
			title1 = getArguments().getString("title");
			phone1 = getArguments().getString("phone");
			email1 = getArguments().getString("email");
		}
		
		email_id=(TextView) view.findViewById(R.id.emailinfo);
		email12 = (TextView) view.findViewById(R.id.email);
		phone = (TextView) view.findViewById(R.id.phone);

		call = (Button) view.findViewById(R.id.callbutton);
		add = (Button) view.findViewById(R.id.addbutton);
		emailid = (Button) view.findViewById(R.id.emailbutton);

		email12.setText(email1);
		phone.setText(phone1);
		getDialog().setTitle(title1);
		
		if ((email12.getText()) == null ||(email12.getText()) == ""){
			emailid.setEnabled(false);
			email12.setEnabled(false);
			email_id.setEnabled(false);
			email_id.setVisibility(8);
		}

		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				getActivity());
		alertDialogBuilder
				.setMessage("This device doesn't support call features")
				.setCancelable(false)
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});


		call.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (pm.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
					String number = "tel:" + phone.getText().toString().trim();
					Intent callIntent = new Intent(Intent.ACTION_CALL, Uri
							.parse(number));
					startActivity(callIntent);
					CallAbility =true;
				}
				else {
					if (!CallAbility == true) {
						AlertDialog alert = alertDialogBuilder
								.create();
						alert.show();
					}
				}
			}
		});
		add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		
				String number = phone.getText().toString().trim();
				Intent intent = new Intent(
						ContactsContract.Intents.Insert.ACTION);
				intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
				intent.putExtra(
						ContactsContract.Intents.Insert.PHONE,
						number);
				intent.putExtra(
						ContactsContract.Intents.Insert.NAME, title1);

				intent.putExtra(
						ContactsContract.Data.IS_SUPER_PRIMARY, 1);
				startActivity(intent);

			}

		});
		emailid.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				final Intent _Intent = new Intent(
						android.content.Intent.ACTION_SEND);
				_Intent.setType("message/rfc822");
				_Intent.putExtra(
						android.content.Intent.EXTRA_EMAIL,
						new String[] { email1 });
				startActivity(Intent
						.createChooser(
								_Intent,
								getString(R.string.title_mail_employment_oppurtunites)));

			}

		});
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		Bundle extras = this.getArguments();

	}

}
