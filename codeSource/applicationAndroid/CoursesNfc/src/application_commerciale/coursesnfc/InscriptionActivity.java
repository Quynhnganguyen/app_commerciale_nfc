package application_commerciale.coursesnfc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InscriptionActivity extends MenuActivity {
	EditText nom;
	EditText prenom;
	EditText adresseMail;
	EditText login;
	EditText mdp;
	Button boutonValiderInscription;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
		nom = (EditText) findViewById(R.id.nom);
		nom.setHint("Nom");
		prenom = (EditText) findViewById(R.id.prenom);
		prenom.setHint("Prenom");
		adresseMail = (EditText) findViewById(R.id.adresseMail);
		adresseMail.setHint("Adresse Email");
		login = (EditText) findViewById(R.id.login);
		login.setHint("Login");
		mdp = (EditText) findViewById(R.id.mdp);
		mdp.setHint("Mot de passe");
		boutonValiderInscription = (Button) findViewById(R.id.boutonValiderInscription);
		boutonValiderInscription.setText("Valider Inscription");
		boutonValiderInscription.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.boutonValiderInscription) {
					Toast.makeText(InscriptionActivity.this, "Merci pour votre inscription "+prenom.getText()+" !",Toast.LENGTH_LONG).show();
					 intent = new Intent(InscriptionActivity.this,AccueilActivity.class);
					startActivity(intent);
				}
			}
		});
		
	}
}

