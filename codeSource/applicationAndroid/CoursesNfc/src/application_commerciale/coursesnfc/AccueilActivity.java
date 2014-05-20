package application_commerciale.coursesnfc;


import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AccueilActivity extends Activity {
	/*Attributs pour l'interface graphique*/
	TextView texteDeBienvenue;
	Button boutonListeMagasins;
	Button boutonProduitsAAcheter;
	Button boutonProduitsFavoris;
	Button boutonListeNoire;
	Button boutonScannerProduit;
	Button boutonComparerDeuxProduits;
	/*Attribut pour les intents*/
	Intent intentRecu;
	final String ID_CLIENT = "id_client";
	Intent intentEnvoye;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		intentRecu = getIntent();
		texteDeBienvenue = (TextView) findViewById(R.id.texteDeBienvenue);
		
		boutonListeMagasins = (Button) findViewById(R.id.boutonListeMagasins);
		boutonListeMagasins.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.boutonListeMagasins) {
					intentEnvoye = new Intent(AccueilActivity.this,MagasinActivity.class);
					intentEnvoye.putExtra(ID_CLIENT,intentRecu.getStringExtra(ID_CLIENT));
					startActivity(intentEnvoye);

				}
			}

		});
		boutonProduitsAAcheter= (Button) findViewById(R.id.boutonProduitsAAcheter);
		boutonProduitsAAcheter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.boutonProduitsAAcheter) {
					intentEnvoye = new Intent(AccueilActivity.this,AccueilActivity.class);
					intentEnvoye.putExtra(ID_CLIENT,intentRecu.getStringExtra(ID_CLIENT));
					startActivity(intentEnvoye);
				}
			}

		});
		boutonProduitsFavoris = (Button) findViewById(R.id.boutonProduitsFavoris);
		boutonProduitsFavoris.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.boutonProduitsFavoris) {
					intentEnvoye = new Intent(AccueilActivity.this,FavorisActivity.class);
					intentEnvoye.putExtra(ID_CLIENT,intentRecu.getStringExtra(ID_CLIENT));
					startActivity(intentEnvoye);			

				}
			}

		});
		boutonListeNoire = (Button) findViewById(R.id.boutonListeNoire);
		boutonListeNoire.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.boutonListeNoire) {
					intentEnvoye = new Intent(AccueilActivity.this,ListeNoireActivity.class);
					intentEnvoye.putExtra(ID_CLIENT,intentRecu.getStringExtra(ID_CLIENT));
					startActivity(intentEnvoye);
				}
			}

		});
		boutonScannerProduit = (Button) findViewById(R.id.boutonScannerProduit);
		boutonScannerProduit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.boutonScannerProduit) {
					intentEnvoye = new Intent(AccueilActivity.this,InformationProduitActivity.class);
					intentEnvoye.putExtra(ID_CLIENT,intentRecu.getStringExtra(ID_CLIENT));
					startActivity(intentEnvoye);
				}
			}

		});
		boutonComparerDeuxProduits= (Button) findViewById(R.id.boutonComparerDeuxProduits);
		boutonComparerDeuxProduits.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.boutonComparerDeuxProduits) {
					intentEnvoye = new Intent(AccueilActivity.this,ComparaisonActivity.class);
					intentEnvoye.putExtra(ID_CLIENT,intentRecu.getStringExtra(ID_CLIENT));
					startActivity(intentEnvoye);
				}
			}

		});
	}

	
}
