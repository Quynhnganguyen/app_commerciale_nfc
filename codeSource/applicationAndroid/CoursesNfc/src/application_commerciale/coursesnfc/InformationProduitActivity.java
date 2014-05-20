package application_commerciale.coursesnfc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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
import android.widget.CheckBox;
import android.widget.TextView;

public class InformationProduitActivity extends MenuActivity {
	/* Attributs pour l'interface graphique */
	TextView scannerProduit;
	TextView intituleNomProduit;
	TextView nomProduit;
	TextView intitulePrixProduit;
	TextView prixProduit;
	TextView intituleTypeProduit;
	TextView typeProduit;
	TextView intituleMagasin;
	TextView magasinProduit;
	TextView intituleSourceProduit;
	TextView sourceProduit;
	CheckBox checkFavoris;
	CheckBox checkListeNoire;

	/* Attributs pour NFC */
	NfcAdapter nfcAdapter;
	private String id_string;

	/* Attributs pour communication avec WebService */
	public static final String TAG = "TAG_INFO_PRODUIT";
	private String nom;
	private String prix;
	private String magasin;
	private String type;
	private String source;
	private String url = "http://quiet-wildwood-3463.herokuapp.com/api/clients/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_information_produit);
		scannerProduit = (TextView) findViewById(R.id.scannerProduit);
		intituleNomProduit = (TextView) findViewById(R.id.intituleNomProduit);
		nomProduit = (TextView) findViewById(R.id.nomProduit);

		intitulePrixProduit = (TextView) findViewById(R.id.intitulePrixProduit);
		prixProduit = (TextView) findViewById(R.id.prixProduit);

		intituleTypeProduit = (TextView) findViewById(R.id.intituleTypeProduit);
		typeProduit = (TextView) findViewById(R.id.typeProduit);

		intituleMagasin = (TextView) findViewById(R.id.intituleMagasin);
		magasinProduit= (TextView) findViewById(R.id.magasinProduit);

		sourceProduit = (TextView) findViewById(R.id.sourceProduit);

		checkFavoris = new CheckBox(this);
		checkListeNoire = new CheckBox(this);

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
			// tagEstScanne = true;
			Log.e(TAG, "appel ws");
			afficheListeMagasins();
			Log.e(TAG, "fin connexion");
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

	/* La connexion au webservice */
	private class InformationProduitTask extends
			AsyncTask<String, Void, String> {
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
				Log.e(TAG, "fdefoejfnfo");
				HttpPost httpPost = new HttpPost(url + "scan_nfc?nfc_id="
						+ id_string);
				Log.e(TAG, "post");
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
					for (int i = 0; i < jSonArray.length(); i++) {
						JSONObject jSonObject = jSonArray.optJSONObject(i);
						nom = jSonObject.getString("produit_nom");
						prix = jSonObject.getString("prix");
						magasin = jSonObject.getString("magasin");
						type = jSonObject.getString("type");
						source = jSonObject.getString("source");
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
			nomProduit.setText(nom);
			prixProduit.setText(prix);
			magasinProduit.setText(magasin);
			typeProduit.setText(type);
			sourceProduit.setText(source);
		}
	}

	/* Appel au WebService */
	public void afficheListeMagasins() {
		new InformationProduitTask().execute(url + "scan_nfc?nfc_id="
				+ id_string);
	}

}
