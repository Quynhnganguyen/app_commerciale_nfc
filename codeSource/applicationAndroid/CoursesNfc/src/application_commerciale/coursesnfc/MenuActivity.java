package application_commerciale.coursesnfc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MenuActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		Intent intent;
		int id = item.getItemId();
		switch (id) {
		case R.id.item1:
			intent = new Intent(MenuActivity.this,AccueilActivity.class);
			startActivity(intent);
			return true;
		case R.id.item2:
			intent = new Intent(MenuActivity.this,MagasinActivity.class);
			startActivity(intent);
			return true;
		case R.id.item3:
			intent = new Intent(MenuActivity.this,TestNfcActivity.class);
			startActivity(intent);
			return true;
		case R.id.item4:
			intent = new Intent(MenuActivity.this,FavorisActivity.class);
			startActivity(intent);
			return true;
		case R.id.item5:
			intent = new Intent(MenuActivity.this,ListeNoireActivity.class);
			startActivity(intent);
			return true;
		case R.id.item6:
			return true;
		case R.id.item7:
			intent = new Intent(MenuActivity.this,ComparaisonActivity.class);
			startActivity(intent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
