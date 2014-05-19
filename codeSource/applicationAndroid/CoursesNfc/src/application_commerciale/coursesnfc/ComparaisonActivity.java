package application_commerciale.coursesnfc;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class ComparaisonActivity extends MenuActivity {
	TextView texteComparaison;
	Spinner typeDeComparaison;
	private String[] lesTypes = { "Source", "Prix", "Lipides", "Glucides",
			"Rentabilité" };
	TextView produit1;
	TextView produit2;
	TextView propositionDeScannerProduit;
	TextView meilleurProduit;
	Button boutonComparaison;
	Button boutonAcheterMeilleurProduit;

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
		boutonAcheterMeilleurProduit =(Button) findViewById(R.id.boutonAcheterMeilleurProduit);

	}

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
			String id_string = ByteArrayToHexString(id);
				nomProduit.setText(id_string);
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

}
