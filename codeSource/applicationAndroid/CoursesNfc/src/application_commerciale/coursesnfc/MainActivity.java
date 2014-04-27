package application_commerciale.coursesnfc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText nomUtilisateur;
	EditText motDePasse;
	Button boutonConnexion;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		nomUtilisateur = (EditText) findViewById(R.id.nomUtilisateur);
		nomUtilisateur.setHint("Utilisateur");
		motDePasse = (EditText) findViewById(R.id.motDePasse);
		motDePasse.setHint("Mot de passe");
		boutonConnexion = (Button) findViewById(R.id.boutonConnexion);
		boutonConnexion.setText("Connexion");
		boutonConnexion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.boutonConnexion) {
					Toast.makeText(MainActivity.this, "Connexion ",Toast.LENGTH_LONG).show();
					Intent intent = new Intent(MainActivity.this,AccueilActivity.class);
					startActivity(intent);
				}
			}

		});
		
	}
	
}
