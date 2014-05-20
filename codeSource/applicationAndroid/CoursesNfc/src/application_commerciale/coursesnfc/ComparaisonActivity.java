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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ComparaisonActivity extends MenuActivity {
	/* Attributs pour l'interface grahique */
	TextView texteComparaison;
	Spinner typeDeComparaison;
	private String[] lesTypes = { "Source", "Prix" };
	TextView produit1;
	TextView produit2;
	TextView propositionDeScannerProduit;
	TextView meilleurProduit;
	Button boutonComparaison;
	Button boutonAcheterMeilleurProduit;

	/* Attributs pour nfc */
	NfcAdapter nfcAdapter;
	private String id_string;
	private boolean premierProduitEstScanne = false;
	private boolean deuxiemeProduitEstScanne = false;

	/* Attributs pour communication avec WebService */
	public static final String TAG = "TAG_INFO_PRODUIT";
	private String nomProduit1;
	private String nomProduit2;
	private String prixProduit1;
	private String prixProduit2;
	private String magasinProduit1;
	private String magasinProduit2;
	private String typeProduit1;
	private String typeProduit2;
	private String sourceProduit1;
	private String sourceProduit2;
	private String url = "http://quiet-wildwood-3463.herokuapp.com/api/clients/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comparaison);

		texteComparaison = (TextView) findViewById(R.id.texteComparaison);

		typeDeComparaison = (Spinner) findViewById(R.id.typeDeComparaison);
		ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, lesTypes);
		typeDeComparaison.setAdapter(adapter_state);
		typeDeComparaison
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub

					}

				});

		produit1 = (TextView) findViewById(R.id.produit1);
		produit2 = (TextView) findViewById(R.id.produit2);
		propositionDeScannerProduit = (TextView) findViewById(R.id.propositionDeScannerProduit);
		meilleurProduit = (TextView) findViewById(R.id.meilleurProduit);
		boutonComparaison = (Button) findViewById(R.id.boutonComparaison);
		boutonAcheterMeilleurProduit = (Button) findViewById(R.id.boutonAcheterMeilleurProduit);

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
			if (!premierProduitEstScanne) {
				premierProduitEstScanne = true;
			}
			if (premierProduitEstScanne & !deuxiemeProduitEstScanne) {
				deuxiemeProduitEstScanne = true;
			}
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

	private class ComparaisonTask extends AsyncTask<String, Void, String> {
		private String response = "";

		@Override
		protected String doInBackground(String... urls) {
			response = recupererInfos();
			return response;
		}

		public String recupererInfos() {
			response = "Ca ne marche pas...";
			HttpClient httpclient = new DefaultHttpClient();
			try {
				HttpPost httpPost = new HttpPost(url + "scan_nfc?nfc_id="
						+ id_string);
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
						if (premierProduitEstScanne) {
							nomProduit1 = jSonObject.getString("produit_nom");
							prixProduit1 = jSonObject.getString("prix");
							magasinProduit1 = jSonObject.getString("magasin");
							typeProduit1 = jSonObject.getString("type");
							sourceProduit1 = jSonObject.getString("source");
						}
						if (premierProduitEstScanne & deuxiemeProduitEstScanne) {
							nomProduit2 = jSonObject.getString("produit_nom");
							prixProduit2 = jSonObject.getString("prix");
							magasinProduit2 = jSonObject.getString("magasin");
							typeProduit2 = jSonObject.getString("type");
							sourceProduit2 = jSonObject.getString("source");
						}
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
			if (deuxiemeProduitEstScanne){
				
			}
		}
	}

	/* Appel au WebService */
	public void afficheInformationsProduit() {
		new ComparaisonTask().execute(url + "scan_nfc?nfc_id=" + id_string);
	}

}
