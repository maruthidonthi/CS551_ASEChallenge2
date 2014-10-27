package umkc.ase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.os.AsyncTask;
import android.util.Log;

/**
 * @author jthd3 Class used to carry on major tasks on other thread than main
 *         thread.
 * 
 */
public class JsonAsyncTask extends AsyncTask<String, Void, String> {

	private String resultJSON;
	JSONArray json;

	public JSONArray getJSON(String url) throws JSONException {

		// Probably put this in a Thread to avoid spending too much time waiting
		// for a result on the main thread
		JSONArray json = new JSONArray(resultJSON);
		return json;
	}

	@Override
	protected String doInBackground(String... url) {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet http = new HttpGet(url[0]);
		try {
			HttpResponse response = client.execute(http);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null)
					builder.append(line);

			}

			else
				Log.e(HoursFragment.class.toString(), "Failed to download file");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		try {
			json = new JSONArray(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
