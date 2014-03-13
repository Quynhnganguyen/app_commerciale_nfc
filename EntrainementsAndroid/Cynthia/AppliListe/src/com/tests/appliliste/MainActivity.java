package com.tests.appliliste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MainActivity extends Activity {
	EditText editName;
	Button buttonValidate;
	TextView indication;
	TextWatcher changeName;
	OnClickListener validateListener;
	ListView listView;
	final String NAME = "name";
	final String CONTACT = "contact";
	final String MAIL = "mail";
	List<HashMap<String, String>> list;
	ListAdapter adapter; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editName = (EditText) findViewById(R.id.editName);
		editName.setHint("Votre nom");

		listView = (ListView) findViewById(R.id.listView);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		String[][] mailBox = new String[][] {
				{ "Winnie Lourson", "wlourson@ourson.fr" },
				{ "Minnie Mouse", "mmouse@mickey.fr" },
				{ "Cendrillon", "cinderella@midnight.fr" } };

		list = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> anElement;

		for (int i = 0; i < mailBox.length; i++) {

			anElement = new HashMap<String, String>();

			anElement.put("text1", mailBox[i][0]);

			anElement.put("text2", mailBox[i][1]);
			list.add(anElement);
		}

		 adapter = new SimpleAdapter(this, list,
				android.R.layout.simple_list_item_2, new String[] { "text1",
						"text2" }, new int[] { android.R.id.text1,
						android.R.id.text2 });
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long id) {
				Intent intentOnItem = new Intent(MainActivity.this,
						Resultat.class);
				HashMap<String, String> elt = list.get(position);
				String nameOfContact = elt.get("text1").toString();
	            String mailOfContact = elt.get("text2").toString();
				intentOnItem.putExtra(CONTACT,nameOfContact);
				intentOnItem.putExtra(MAIL, mailOfContact);
				startActivity(intentOnItem);
			}
		});

		buttonValidate = (Button) findViewById(R.id.buttonValidate);
		buttonValidate.setText("Valider");
		buttonValidate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.buttonValidate) {
					String n = editName.getText().toString();
					Toast.makeText(MainActivity.this, "Merci d'avoir rempli le formulaire " + n,
							Toast.LENGTH_LONG).show();
					Intent intent = new Intent(MainActivity.this,
							Resultat.class);
					intent.putExtra(NAME, editName.getText().toString());

					startActivity(intent);
				}
			}

		});
		
		indication = (TextView) findViewById(R.id.indication);
		indication.setTextColor(Color.BLUE);
		indication.setText("Remplissez le formulaire");
		indication.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				indication.setText("Il faut valider pour voir le resultat");
			}

		});
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
