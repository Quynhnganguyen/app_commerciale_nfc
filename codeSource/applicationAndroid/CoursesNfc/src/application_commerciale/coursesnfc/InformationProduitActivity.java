package application_commerciale.coursesnfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class InformationProduitActivity extends MenuActivity {
	TextView scannerProduit;
	TextView intituleNomProduit;
	TextView nomProduit;
	TextView intitulePrixProduit;
	TextView prixProduit;
	TextView intituleTypeProduit;
	TextView typeProduit;
	TextView intituleFranchise;
	TextView franchise;
	TextView intituleSourceProduit;
	TextView sourceProduit;
	CheckBox checkFavoris;
	CheckBox checkListeNoire;

	NfcAdapter nfcAdapter;

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

		intituleFranchise = (TextView) findViewById(R.id.intituleFranchise);
		franchise = (TextView) findViewById(R.id.franchise);

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
