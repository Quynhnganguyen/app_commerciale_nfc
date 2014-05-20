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

public class ListeProduitsActivity extends MenuActivity {
	ListView listeViewProduitsMagasin;
	private List<HashMap<String, String>> produitsMagasin;
	ListAdapter adapter;
	private String[][] lesproduitsMagasin;

	/* Attributs pour Intent */
	Intent intentRecu;
	private final String ID_MAGASIN = "id_magasin";

	/* Attributs pour la connexion avec le WebService */
	public static final String TAG = "TAG_PRODUITSMAGASIN";
	private String url = "http://quiet-wildwood-3463.herokuapp.com/api/clients/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		intentRecu = getIntent();
		setContentView(R.layout.activity_liste_produits_magasin);
		listeViewProduitsMagasin = (ListView) findViewById(R.id.listeProduitsMagasin;
		listeViewProduitsMagasin.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		produitsMagasin = new ArrayList<HashMap<String, String>>();
		
		afficheProduitsMagasin();
	}

	private class ProduitsMagasinTask extends AsyncTask<String, Void, String> {
		private String response = "";

		@Override
		protected String doInBackground(String... urls) {
			response = recupererProduitsMagasin();
			return response;
		}

		public String recupererProduitsMagasin() {
			response = "Ca ne marche pas...";
			HttpClient httpclient = new DefaultHttpClient();
			try {
				HttpPost httpPost = new HttpPost(url
						+ "produits_magasin?magasin_id="
						+ intentRecu.getStringExtra(ID_MAGASIN));
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
					lesproduitsMagasin = new String[jSonArray.length()][2];
					for (int i = 0; i < jSonArray.length(); i++) {
						JSONObject jSonObject = jSonArray.optJSONObject(i);
						String nom = jSonObject.getString("produit_nom");
						String prix = jSonObject.getString("prix");

						lesproduitsMagasin[i][0] = nom;
						lesproduitsMagasin[i][1] = prix;
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

				HashMap<String, String> unProduitMagasin;

				for (int i = 0; i < lesproduitsMagasin.length; i++) {

					unProduitMagasin = new HashMap<String, String>();

					unProduitMagasin.put("nom", lesproduitsMagasin[i][0]);

					unProduitMagasin.put("prix", lesproduitsMagasin[i][1]);
					produitsMagasin.add(unProduitMagasin);
				}

				adapter = new SimpleAdapter(getApplicationContext(), produitsMagasin,
						android.R.layout.simple_list_item_2, new String[] { "nom",
								"prix" }, new int[] { android.R.id.nom,
								android.R.id.prix });
				listeViewProduitsMagasin.setAdapter(adapter);


			} else {
				Log.e(TAG, "Ca ne marche pas");
			}
		}
	}

	public void afficheListeNoire() {
		new ProduitsMagasinTask().execute(url + "produits_magasin?magasin_id="
				+ intentRecu.getStringExtra(ID_MAGASIN));
	}

}
