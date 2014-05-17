package example.connexionbdd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {
	public Toast toast;
	public static final String TAG = "Mon_TAG";
	public TextView letexte;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		letexte = (TextView) findViewById(R.id.texte);
		Button btn = (Button) findViewById(R.id.bouton);
		btn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Affiche();
			}
		});
	}

	public void Affiche() {
		new RequestTask()
				.execute("http://quiet-wildwood-3463.herokuapp.com/api/clients/liste_magasins");
	}

	private class RequestTask extends AsyncTask<String, Void, String> {
		private String response = "";

		@Override
		protected String doInBackground(String... urls) {
			response = Recup_WS_domo();
			return response;
		}

		public String Recup_WS_domo() {
			response = "Ca ne marche pas...";
			HttpClient httpclient = new DefaultHttpClient();
			try {
				HttpGet httpGet = new HttpGet(
						"http://quiet-wildwood-3463.herokuapp.com/magasins/index?action=get");
				HttpResponse httpresponse = httpclient.execute(httpGet);
				HttpEntity httpentity = httpresponse.getEntity();
				if (httpentity != null) {
					InputStream inputstream = httpentity.getContent();
					BufferedReader bufferedreader = new BufferedReader(
							new InputStreamReader(inputstream));
					StringBuilder strinbulder = new StringBuilder();
					String ligne = bufferedreader.readLine();
					while (ligne != null) {
						strinbulder.append(ligne + "n");
						ligne = bufferedreader.readLine();
					}
					bufferedreader.close();
					
					JSONArray ja = new JSONArray(strinbulder.toString());
					List<String> allNames = new ArrayList<String>();
					for (int i =0;i<ja.length();i++){
						JSONObject actor = ja.optJSONObject(i);
						String name = actor.getString("magasin_nom");
						allNames.add(name);
					}
					
					response = allNames.get(1);
				}
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			letexte.setText(result);
		}
	}
}