package application_commerciale.coursesnfc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConnexionActivity extends Activity {
	/* Attributs pour l'interface graphique */
	EditText nomUtilisateur;
	EditText motDePasse;
	Button boutonConnexion;
	TextView inscription;
	Intent intent;
	
	/*Attributs pour le intent*/
	private final String ID_CLIENT="id_client";

	/* Attributs pour communication avec WebService */
	@SuppressWarnings("unused")
	private String nomRecupere;
	private String idRecupere;
	private String messageRecupere;
	public static final String TAG = "TAG_INFO_PRODUIT";
	private String url = "http://quiet-wildwood-3463.herokuapp.com/api/clients/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_connexion);

		nomUtilisateur = (EditText) findViewById(R.id.nomUtilisateur);
		motDePasse = (EditText) findViewById(R.id.motDePasse);
		boutonConnexion = (Button) findViewById(R.id.boutonConnexion);
		boutonConnexion.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.boutonConnexion) {
					verifierExistenceClient();

				}
			}

		});

		inscription = (TextView) findViewById(R.id.inscription);
		inscription.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				inscription.setTextColor(Color.WHITE);
				intent = new Intent(ConnexionActivity.this,
						InscriptionActivity.class);
				startActivity(intent);
			}

		});
	}

	/* La connexion au webservice */
	private class ConnexionTask extends AsyncTask<String, Void, String> {
		private String response = "";

		@Override
		protected String doInBackground(String... urls) {
			response = recupererLesMagasins();
			return response;
		}

		public String recupererLesMagasins() {
			/*Connexion au WebService*/
			response = "Problème de connexion avec le WebService";
			HttpClient httpclient = new DefaultHttpClient();
			try {
				HttpPost httpPost = new HttpPost(url + "sign_in?email="
						+ nomUtilisateur.getText() + "&password="
						+ motDePasse.getText());
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

					/*Parsage des informations récupérées*/
					JSONObject jSonObject = new JSONObject(
							stringBuilder.toString());

					messageRecupere = jSonObject.getString("message");
					if (messageRecupere.equals("Login success")) {
						nomRecupere = jSonObject.getString("clientname");
						idRecupere= jSonObject.getString("client_id");
					}

					response = "Connexion ok";
				}
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			if (messageRecupere.equals("Invalid client")) {
				Toast.makeText(
						ConnexionActivity.this,
						"Nom utilisateur incorrect! Rééssayez s'il vous plaît!",
						Toast.LENGTH_LONG).show();
			} else if (messageRecupere.equals("Invalid password")) {
				Toast.makeText(ConnexionActivity.this,
						"Mot de passe erroné! Rééssayez s'il vous plaît!",
						Toast.LENGTH_LONG).show();
			} else if (messageRecupere.equals("Login success")) {
				Toast.makeText(ConnexionActivity.this, "Connexion ",
						Toast.LENGTH_LONG).show();
				intent = new Intent(ConnexionActivity.this, MenuActivity.class);
				intent.putExtra(ID_CLIENT,idRecupere);
				startActivity(intent);
				intent = new Intent(ConnexionActivity.this, AccueilActivity.class);
				intent.putExtra(ID_CLIENT,idRecupere);
				startActivity(intent);
				
				
			}
		}
	}

	/* Appel au WebService */
	public void verifierExistenceClient() {
		new ConnexionTask().execute(url + "sign_in?email="
				+ nomUtilisateur.getText() + "&password="
				+ motDePasse.getText());
	}

	@Override  
	public void onBackPressed() {
	    super.onBackPressed();   
	    finish();
	    startActivity(getIntent());
	}
}
