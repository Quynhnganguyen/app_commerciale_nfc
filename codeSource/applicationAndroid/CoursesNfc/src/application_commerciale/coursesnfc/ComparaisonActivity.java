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

}
