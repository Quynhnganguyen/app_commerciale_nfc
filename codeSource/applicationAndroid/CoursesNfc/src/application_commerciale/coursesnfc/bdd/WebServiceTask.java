package application_commerciale.coursesnfc.bdd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import application_commerciale.coursesnfc.MagasinActivity;

public class WebServiceTask extends AsyncTask<String, Void, String> {
	private String reponseDuWS;
	private String url = "http://quiet-wildwood-3463.herokuapp.com/api/clients/";
	private static String TAG;
	private JSONArray lesDonnees;
	private Context context;

	@Override
	protected String doInBackground(String... urls) {
		communiquerAvecWS();
		return reponseDuWS;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String suiteUrl) {
		url += suiteUrl;

	}

	public JSONArray getLesDonnees() {
		return lesDonnees;
	}

	public void communiquerAvecWS() {

		reponseDuWS = "Erreur lors de la récupération des données";
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpresponse = httpclient.execute(httpGet);
			HttpEntity httpentity = httpresponse.getEntity();
			if (httpentity != null) {
				InputStream inputstream = httpentity.getContent();
				BufferedReader bufferedreader = new BufferedReader(
						new InputStreamReader(inputstream));
				StringBuilder stringBuilder = new StringBuilder();
				String ligne = bufferedreader.readLine();
				while (ligne != null) {
					stringBuilder.append(ligne + "n");
					ligne = bufferedreader.readLine();
				}
				bufferedreader.close();

				lesDonnees = new JSONArray(stringBuilder.toString());
				/*
				 * List<String> list = new ArrayList<String>(); for (int i = 0;
				 * i < lesDonnees.length(); i++) { JSONObject actor =
				 * lesDonnees.optJSONObject(i); String name =
				 * actor.getString("result"); list.add(name); }
				 */

				reponseDuWS = "Donnees récupérées";
			}
		} catch (Exception e) {
			Log.e(TAG, e.getMessage());
		}

	}

	@Override
	protected void onPostExecute(String result) {
	
	}
}
