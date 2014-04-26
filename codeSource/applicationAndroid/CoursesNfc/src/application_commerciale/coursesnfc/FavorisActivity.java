package application_commerciale.coursesnfc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.os.Build;

public class FavorisActivity extends ActionBarActivity {
	ListView listeFavoris;
	List<HashMap<String, String>> produitsFavoris;
	ListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favoris);
		listeFavoris = (ListView) findViewById(R.id.listeFavoris);
		listeFavoris.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		String[][] lesProduitsFavoris = new String[][] {
				{ "Produit 1", "Magasin 2" }, { "Produit 2", "Magasin 2" },
				{ "Produit 3", "Magasin 1" } };

		produitsFavoris = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> unProduitFavoris;

		for (int i = 0; i < lesProduitsFavoris.length; i++) {

			unProduitFavoris = new HashMap<String, String>();

			unProduitFavoris.put("text1", lesProduitsFavoris[i][0]);

			unProduitFavoris.put("text2", lesProduitsFavoris[i][1]);
			produitsFavoris.add(unProduitFavoris);
		}

		adapter = new SimpleAdapter(this, produitsFavoris,
				android.R.layout.simple_list_item_2, new String[] { "text1",
						"text2" }, new int[] { android.R.id.text1,
						android.R.id.text2 });
		listeFavoris.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favoris, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
