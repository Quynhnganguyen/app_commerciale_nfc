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

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ProduitsAAcheterActivity extends MenuActivity {
	/* Attributs pour l'interface graphique */
	ListView listeViewAAcheter;
	private List<HashMap<String, String>> produitsAAcheter;
	ListAdapter adapter;
	private String[][] lesProduitsAAcheter;
	TextView prixTotal;
	TextView scannerProduitAAcheter;
	float totalCourses = 0;

	/* Attributs pour nfc */
	NfcAdapter nfcAdapter;
	private String id_string;
	private boolean nouveauProduitScanne = false;

	/* Attributs pour Intent */
	Intent intentRecu;
	private final String ID_CLIENT = "id_client";

	/* Attributs pour la connexion avec le WebService */
	public static final String TAG = "TAG_AACHETER";
	private String url = "http://quiet-wildwood-3463.herokuapp.com/api/clients/";
	@SuppressWarnings("unused")
	private String idNouveauProduit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_produits_aacheter);
		intentRecu = getIntent();
		scannerProduitAAcheter = (TextView) findViewById(R.id.scannerProduitAAcheter);
		prixTotal = (TextView) findViewById(R.id.prixTotal);
		listeViewAAcheter = (ListView) findViewById(R.id.listeAAcheter);
		listeViewAAcheter.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		produitsAAcheter = new ArrayList<HashMap<String, String>>();

		afficherProduitsAAcheter();

		/* On récupère va permettre à l'activité de récupérer un tag NFC */
		this.nfcAdapter = NfcAdapter.getDefaultAdapter(this);
		if (nfcAdapter == null || !nfcAdapter.isEnabled()) {
			finish();
			return;
		}
	}

	/* Le code pour NFC */
	@Override
	protected void onResume() {
		super.onResume();
		Intent intent = new Intent(this, this.getClass())
				.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				intent, 0);
		IntentFilter[] filters = null;
		String[][] techLists = null;
		nfcAdapter.enableForegroundDispatch(this, pendingIntent, filters,
				techLists);
	}

	@Override
	protected void onPause() {
		super.onPause();
		nfcAdapter.disableForegroundDispatch(this);
	}

	private void resolveIntent(Intent intent) {
		String action = intent.getAction();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
			byte[] id = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
			id_string = ByteArrayToHexString(id);
			nouveauProduitScanne = true;
			afficherProduitsAAcheter();

		}
	}

	private String ByteArrayToHexString(byte[] inarray) {
		int i, j, in;
		String[] hex = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a",
				"b", "c", "d", "e", "f" };
		String out = "";

		for (j = 0; j < inarray.length; ++j) {
			in = (int) inarray[j] & 0xff;
			i = (in >> 4) & 0x0f;
			out += hex[i];
			i = in & 0x0f;
			out += hex[i];
		}
		return out;
	}

	@Override
	public void onNewIntent(Intent intent) {
		setIntent(intent);
		resolveIntent(intent);
	}

	private class ProduitsAAcheterTask extends AsyncTask<String, Void, String> {
		private String response = "";

		@Override
		protected String doInBackground(String... urls) {
			response = recupererProduitsAAcheter();
			return response;
		}

		public String recupererProduitsAAcheter() {
			response = "Ca ne marche pas...";
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost;
			try {
				if (!nouveauProduitScanne) {
					httpPost = new HttpPost(url
							+ "show_liste_acheter?client_id="
							+ intentRecu.getStringExtra(ID_CLIENT));
				} else {

					httpPost = new HttpPost(url + "scan_nfc?nfc_id="
							+ id_string);
				}
				StringBuilder stringBuilder = requeteHttp(httpclient, httpPost);

				if (!nouveauProduitScanne) {
					JSONArray jSonArray = new JSONArray(
							stringBuilder.toString());
					lesProduitsAAcheter = new String[jSonArray.length()][2];
					for (int i = 0; i < jSonArray.length(); i++) {
						JSONObject jSonObject = jSonArray.optJSONObject(i);
						String nom = jSonObject.getString("produit_nom");
						String prix = jSonObject.getString("prix");

						lesProduitsAAcheter[i][0] = nom;
						lesProduitsAAcheter[i][1] = prix;
					}

				} else {
					JSONArray jSonArray = new JSONArray(
							stringBuilder.toString());
					String idNouveauProduit;

					JSONObject jSonObject = jSonArray.optJSONObject(0);
					idNouveauProduit = jSonObject.getString("produit_id");
					Log.e(TAG, "nfkefn");
					httpPost = new HttpPost(url
							+ "add_liste_acheter?client_id="
							+ intentRecu.getStringExtra(ID_CLIENT)
							+ "&produit_id=" + idNouveauProduit);
					StringBuilder sBuilder = requeteHttp(httpclient, httpPost);
					JSONObject jObject = new JSONObject(sBuilder.toString());
					response = jObject.getString("message");
					finish();
					startActivity(intentRecu);

				}

				response = "Données récupérées";
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			return response;
		}

		public StringBuilder requeteHttp(HttpClient httpclient,
				HttpPost httpPost) {
			try {
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
					return stringBuilder;
				}
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result.equals("Données récupérées")) {

				HashMap<String, String> unProduitAAcheter;

				for (int i = 0; i < lesProduitsAAcheter.length; i++) {

					unProduitAAcheter = new HashMap<String, String>();

					unProduitAAcheter.put("text1", lesProduitsAAcheter[i][0]);

					unProduitAAcheter.put("text2", lesProduitsAAcheter[i][1]);
					produitsAAcheter.add(unProduitAAcheter);
					totalCourses += Float.parseFloat(lesProduitsAAcheter[i][1]);
					prixTotal.setText(getResources().getString(
							R.string.prixTotal, totalCourses));
				}

				adapter = new SimpleAdapter(getApplicationContext(),
						produitsAAcheter, android.R.layout.simple_list_item_2,
						new String[] { "text1", "text2" }, new int[] {
								android.R.id.text1, android.R.id.text2 });
				listeViewAAcheter.setAdapter(adapter);

			} else {
				Log.e(TAG, "Ca ne marche pas");
			}
		}
	}

	public void afficherProduitsAAcheter() {
		if (!nouveauProduitScanne) {
			new ProduitsAAcheterTask().execute(url
					+ "show_liste_acheter?client_id="
					+ intentRecu.getStringExtra(ID_CLIENT));
		} else {
			new ProduitsAAcheterTask().execute(url + "scan_nfc?nfc_id="
					+ id_string);
		}
	}

}
