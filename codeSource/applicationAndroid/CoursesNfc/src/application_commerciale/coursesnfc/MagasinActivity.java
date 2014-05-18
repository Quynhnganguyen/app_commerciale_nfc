package application_commerciale.coursesnfc;

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
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MagasinActivity extends MenuActivity {
	private ListView listeViewMagasins;
	private String[][] lesMagasins;
	private List<HashMap<String, String>> listeMagasins;
	private ListAdapter adapter;
	public static final String TAG = "TAG_MAGASINS";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_magasin);
<<<<<<< HEAD
		listeMagasins = (ListView) findViewById(R.id.listeMagasins);
		  listeMagasins.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		magasins = new ArrayList<HashMap<String, String>>();
=======

		listeViewMagasins = (ListView) findViewById(R.id.listeMagasins);
		listeViewMagasins.setChoiceMode(ListView.CHOICE_MODE_NONE);
		listeMagasins = new ArrayList<HashMap<String, String>>();
		afficheListeMagasins();
	}
>>>>>>> ab5ab3dd69b59a87a14b8ea6f2212996bc923f4f

	public void afficheListeMagasins() {
		new MagasinTask()
				.execute("http://quiet-wildwood-3463.herokuapp.com/magasins/index?action=get");
	}

<<<<<<< HEAD
		
		String[][] lesMagasins = new String[][]

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
						String adresse = actor.getString("magasin_adresse");
	
						unMagasin = new HashMap<String, String>();

						unMagasin.put("text1", name);

						unMagasin.put("text2", adresse);
						magasins.add(unMagasin);
					}
					
				}
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
	}
=======
	private class MagasinTask extends AsyncTask<String, Void, String> {
		private String response = "";

		@Override
		protected String doInBackground(String... urls) {
			response = recupererLesMagasins();
			return response;
		}

		public String recupererLesMagasins() {
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
					StringBuilder stringBuilder = new StringBuilder();
					String ligne = bufferedreader.readLine();
					while (ligne != null) {
						stringBuilder.append(ligne + "n");
						ligne = bufferedreader.readLine();
					}
					bufferedreader.close();
					
					JSONArray jSonArray = new JSONArray(stringBuilder.toString());
					lesMagasins = new String[jSonArray.length()][2];
					for (int i = 0; i < jSonArray.length(); i++) {
						JSONObject jSonObject = jSonArray.optJSONObject(i);
						String nom = jSonObject.getString("magasin_nom");
						String adresse = jSonObject
								.getString("magasin_addresse");
					
						lesMagasins[i][0]= nom;
						lesMagasins[i][1]= adresse;
					}

					response = "ok";
				}
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result.equals("ok")) {
				HashMap<String, String> unMagasin;
>>>>>>> ab5ab3dd69b59a87a14b8ea6f2212996bc923f4f

				for (int i = 0; i < lesMagasins.length; i++) {

					unMagasin = new HashMap<String, String>();

					unMagasin.put("text1", lesMagasins[i][0]);

					unMagasin.put("text2", lesMagasins[i][1]);
					listeMagasins.add(unMagasin);
				}
				
				adapter = new SimpleAdapter(getApplicationContext(),
						listeMagasins, android.R.layout.simple_list_item_2,
						new String[] { "text1", "text2" }, new int[] {
								android.R.id.text1, android.R.id.text2 });
				listeViewMagasins.setAdapter(adapter);

			
			} else {
				Log.e(TAG, "Ca ne marche pas");
			}
		}
	}

}
