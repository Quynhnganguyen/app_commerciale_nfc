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
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

	/* Attributs pour Intent */
	Intent intentRecu;
	private final String ID_CLIENT = "id_client";

	/* Attributs pour communication avec WebService */
	public static final String TAG = "TAG_INFO_PRODUIT";
	private String idProduit1;
	private String idProduit2;
	private String nomProduit1;
	private String nomProduit2;
	private String prixProduit1;
	private String prixProduit2;
	private String sourceProduit1;
	private String sourceProduit2;
	// attribut qui détermine si on appelle le webService pour acheter le
	// meilleur produit
	private boolean veutAcheterBestProduit = false;
	private String bestProduit;
	private String url = "http://quiet-wildwood-3463.herokuapp.com/api/clients/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comparaison);
		intentRecu = getIntent();
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

		boutonComparaison = (Button) findViewById(R.id.boutonComparaison);

		boutonComparaison.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (premierProduitEstScanne & deuxiemeProduitEstScanne) {
					if ((typeDeComparaison.getSelectedItem()).equals("Prix")) {
						if (Float.parseFloat(prixProduit1) < Float
								.parseFloat(prixProduit2)) {
							propositionDeScannerProduit
									.setText("Meilleur produit -> produit 1: "
											+ prixProduit1 + " €");
							boutonAcheterMeilleurProduit.setEnabled(true);
							bestProduit = idProduit1;

						} else {
							propositionDeScannerProduit
									.setText("Meilleur produit -> produit2: "
											+ prixProduit2 + " €");
							boutonAcheterMeilleurProduit.setEnabled(true);
							bestProduit = idProduit2;
						}
					} else {
						propositionDeScannerProduit.setText("Produit1 : "
								+ sourceProduit1 + " Produit2 "
								+ sourceProduit2);
					}
				}
			}

		});

		boutonAcheterMeilleurProduit = (Button) findViewById(R.id.boutonAcheterMeilleurProduit);
		boutonAcheterMeilleurProduit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				veutAcheterBestProduit = true;
				appelerWebService();

			}

		});
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
			if (premierProduitEstScanne & !deuxiemeProduitEstScanne) {
				deuxiemeProduitEstScanne = true;
				appelerWebService();
			}
			if (!premierProduitEstScanne & !deuxiemeProduitEstScanne) {
				premierProduitEstScanne = true;
				appelerWebService();
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
				HttpPost httpPost;
				if (!veutAcheterBestProduit) {
					httpPost = new HttpPost(url + "scan_nfc?nfc_id="
							+ id_string);
				} else {
					
					httpPost = new HttpPost(url
							+ "add_liste_acheter?client_id="
							+ intentRecu.getStringExtra(ID_CLIENT)
							+ "&produit_id=" + bestProduit);
				}
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
					if (!veutAcheterBestProduit) {
						JSONArray jSonArray = new JSONArray(
								stringBuilder.toString());
						for (int i = 0; i < jSonArray.length(); i++) {
							JSONObject jSonObject = jSonArray.optJSONObject(i);

							if (premierProduitEstScanne
									& !deuxiemeProduitEstScanne) {
								idProduit1 = jSonObject.getString("produit_id");
								nomProduit1 = jSonObject
										.getString("produit_nom");
								prixProduit1 = jSonObject.getString("prix");
								sourceProduit1 = jSonObject.getString("source");
							}
							if (premierProduitEstScanne
									& deuxiemeProduitEstScanne) {
								idProduit2 = jSonObject.getString("produit_id");
								nomProduit2 = jSonObject
										.getString("produit_nom");
								prixProduit2 = jSonObject.getString("prix");
								sourceProduit2 = jSonObject.getString("source");
							}

						}
						response = "ok";
					}else{
						
						JSONObject jSonObject = new JSONObject(
								stringBuilder.toString());

						response = jSonObject.getString("message");
						Log.e(TAG,response);
					}

					
				}
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			if (!veutAcheterBestProduit) {
				if (premierProduitEstScanne & !deuxiemeProduitEstScanne) {
					produit1.setText(nomProduit1);
					propositionDeScannerProduit
							.setText("Scannez le deuxieme produit");
				}
				if (premierProduitEstScanne & deuxiemeProduitEstScanne) {
					produit2.setText(nomProduit2);
					boutonComparaison.setEnabled(true);
					propositionDeScannerProduit.setText("Appuyez sur comparer");
				}
			} else {
				Toast.makeText(ComparaisonActivity.this, "Produit ajouté",
						Toast.LENGTH_LONG).show();
			}

		}
	}

	/* Appel au WebService */
	public void appelerWebService() {
		if (!veutAcheterBestProduit) {
		new ComparaisonTask().execute(url + "scan_nfc?nfc_id=" + id_string);}
		else{
			new ComparaisonTask().execute(url
					+ "add_liste_acheter?client_id="
					+ intentRecu.getStringExtra(ID_CLIENT)
					+ "&produit_id=" + bestProduit);	
		}
	}

}
