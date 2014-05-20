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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListeNoireActivity extends MenuActivity {
	ListView listeViewNoire;
	private List<HashMap<String, String>> produitsNoirs;
	ListAdapter adapter;
	private String[][] lesProduitsNoirs;

	/* Attributs pour Intent */
	Intent intentRecu;
	private final String ID_CLIENT = "id_client";

	/* Attributs pour la connexion avec le WebService */
	public static final String TAG = "TAG_LISTENOIRE";
	private String url = "http://quiet-wildwood-3463.herokuapp.com/api/clients/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		intentRecu = getIntent();
		setContentView(R.layout.activity_liste_noire);
		listeViewNoire = (ListView) findViewById(R.id.listeNoire);
		listeViewNoire.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		produitsNoirs = new ArrayList<HashMap<String, String>>();
		
		afficheListeNoire();
	}

	private class ListeNoireTask extends AsyncTask<String, Void, String> {
		private String response = "";

		@Override
		protected String doInBackground(String... urls) {
			response = recupererProduitsFavoris();
			return response;
		}

		public String recupererProduitsFavoris() {
			response = "Ca ne marche pas...";
			HttpClient httpclient = new DefaultHttpClient();
			try {
				HttpPost httpPost = new HttpPost(url
						+ "show_liste_noire?client_id="
						+ intentRecu.getStringExtra(ID_CLIENT));
				HttpResponse httpresponse = httpclient.execute(httpPost);
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

					JSONArray jSonArray = new JSONArray(
							stringBuilder.toString());
					lesProduitsNoirs = new String[jSonArray.length()][2];
					for (int i = 0; i < jSonArray.length(); i++) {
						JSONObject jSonObject = jSonArray.optJSONObject(i);
						String nom = jSonObject.getString("produit_nom");
						String prix = jSonObject.getString("prix");

						lesProduitsNoirs[i][0] = nom;
						lesProduitsNoirs[i][1] = prix;
					}

					response = "Données récupérées";
				}
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result.equals("Données récupérées")) {

				HashMap<String, String> unProduitNoir;

				for (int i = 0; i < lesProduitsNoirs.length; i++) {

					unProduitNoir = new HashMap<String, String>();

					unProduitNoir.put("text1", lesProduitsNoirs[i][0]);

					unProduitNoir.put("text2", lesProduitsNoirs[i][1]);
					produitsNoirs.add(unProduitNoir);
				}

				adapter = new SimpleAdapter(getApplicationContext(), produitsNoirs,
						android.R.layout.simple_list_item_2, new String[] { "text1",
								"text2" }, new int[] { android.R.id.text1,
								android.R.id.text2 });
				listeViewNoire.setAdapter(adapter);


			} else {
				Log.e(TAG, "Ca ne marche pas");
			}
		}
	}

	public void afficheListeNoire() {
		new ListeNoireTask().execute(url + "show_liste_noire?client_id="
				+ intentRecu.getStringExtra(ID_CLIENT));
	}

}
