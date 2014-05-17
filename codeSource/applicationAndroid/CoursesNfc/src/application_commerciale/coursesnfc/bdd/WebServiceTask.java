package application_commerciale.coursesnfc.bdd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class WebServiceTask extends AsyncTask<String, Void, String> {
	private String reponseDuWS;
	private String url;
	private static String TAG;
	private JSONArray jSonArray;

	@Override
	protected String doInBackground(String... urls) {
		reponseDuWS = communiquerAvecWS();
		return reponseDuWS;
	}

	public String communiquerAvecWS() {
			reponseDuWS = "Erreur lors de la récupération des données";
			HttpClient httpclient = new DefaultHttpClient();
			try {
				HttpGet httpGet = new HttpGet(url);
				HttpResponse httpResponse = httpclient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				if (httpEntity != null) {
					InputStream inputstream = httpEntity.getContent();
					BufferedReader bufferedreader = new BufferedReader(
							new InputStreamReader(inputstream));		
					String ligne = bufferedreader.readLine();
					while (ligne != null) {
						ligne = bufferedreader.readLine();
					}
					bufferedreader.close();
								
					jSonArray = new JSONArray();
					List<String> lesDonnees = new ArrayList<String>();
					for (int i =0;i<jSonArray.length();i++){
						JSONObject actor = jSonArray.optJSONObject(i);
						String name = actor.getString("result");
						lesDonnees.add(name);
					}
					
					reponseDuWS = "Donnees récupérées" ;
				}
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			return reponseDuWS;
		}

	@Override
	protected void onPostExecute(String result) {

	}
}
