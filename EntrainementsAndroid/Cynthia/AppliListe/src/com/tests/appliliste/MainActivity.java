package com.tests.appliliste;

import android.os.Bundle;
import android.app.Activity;
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
	TextView textName;
	TextWatcher changeName;
	OnClickListener validateListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editName = (EditText) findViewById(R.id.editName);
		editName.setHint("Jacky");

		buttonValidate = (Button) findViewById(R.id.buttonValidate);
		buttonValidate.setText("Valider");
		buttonValidate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (v.getId() == R.id.buttonValidate) {
					String n = editName.getText().toString();
					Toast.makeText(MainActivity.this, n, Toast.LENGTH_LONG)
							.show();
				}
			}

		});

		textName = (TextView) findViewById(R.id.textName);
		textName.setTextColor(Color.BLUE);
		textName.setText("Remplissez le formulaire");
		textName.addTextChangedListener(new TextWatcher() {

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
				textName.setText("Il faut valider pour voir le resultat");
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
