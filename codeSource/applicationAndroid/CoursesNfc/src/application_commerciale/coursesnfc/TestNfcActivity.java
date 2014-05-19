package application_commerciale.coursesnfc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.TextView;
import android.widget.Toast;

public class TestNfcActivity extends Activity {
	NfcAdapter nfcAdapter;
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_nfc);
		this.textView = (TextView) findViewById(R.id.textView1);
		this.nfcAdapter = NfcAdapter.getDefaultAdapter(this);

		if (nfcAdapter == null || !nfcAdapter.isEnabled()) {
			Toast.makeText(this, "no nfc", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}
		resolveIntent(this.getIntent());
	}
	@Override
	protected void onResume() {
	    super.onResume();
	    Intent intent = new Intent(this, this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
	    PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
	    IntentFilter[] filters = null;
	    String[][] techListArray = null;
	    nfcAdapter.enableForegroundDispatch(this, pendingIntent, filters, techListArray);
	}
	@Override
	protected void onPause() {
	    super.onPause();
	    nfcAdapter.disableForegroundDispatch(this);
	}
	
	private void resolveIntent(Intent intent) {
	    String action = intent.getAction();
	    if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
	        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
	        if (rawMsgs != null) {
	            NdefMessage[] messages = new NdefMessage[rawMsgs.length];
	            for (int i = 0; i < rawMsgs.length; i++) {
	                messages[i] = (NdefMessage) rawMsgs[i];
	            }
	            // Exemple basique de récupération des données dans le tableau
	            String str = new String(messages[0].getRecords()[0].getPayload());
	            textView.setText(str);
	        }
	    }
	}

	@Override
	public void onNewIntent(Intent intent) 
	{
	    setIntent(intent);
	    resolveIntent(intent);
	}

}
