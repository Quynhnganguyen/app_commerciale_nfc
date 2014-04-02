package com.tests.appliliste;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Resultat extends Activity {
	final String NAME = "name";
	final String CONTACT = "contact";
	final String MAIL = "mail";
	Intent intent ;
	Intent intentOnItem;
	TextView name;
	Button returnButton;
	TextView idContact;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultat);
		intent = getIntent();
		name = (TextView) findViewById(R.id.name);
		if (intent != null) {
			if(intent.getStringExtra(NAME)!=null)
			name.setText("Bienvenue " + intent.getStringExtra(NAME) + " J'espère que vous allez bien!");
		}
		intentOnItem = getIntent();
		idContact = (TextView) findViewById(R.id.idContact);
		if (intentOnItem != null) {
			if ((intentOnItem.getStringExtra(CONTACT) != null) && (intentOnItem.getStringExtra(MAIL) != null))
			idContact.setText("Vous avez décidé d'envoyer un mail à " + intentOnItem.getStringExtra(CONTACT));
		}

		returnButton = (Button) findViewById(R.id.returnButton);
		returnButton.setText("Retour");
		returnButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(Resultat.this, MainActivity.class);
				startActivity(intent2);
			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultat, menu);
		return true;
	}

}
