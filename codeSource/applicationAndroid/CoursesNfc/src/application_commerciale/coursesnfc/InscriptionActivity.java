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
		prenom = (EditText) findViewById(R.id.prenom);
		adresseMail = (EditText) findViewById(R.id.adresseMail);
		login = (EditText) findViewById(R.id.login);
		mdp = (EditText) findViewById(R.id.mdp);
		boutonValiderInscription = (Button) findViewById(R.id.boutonValiderInscription);
		
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

