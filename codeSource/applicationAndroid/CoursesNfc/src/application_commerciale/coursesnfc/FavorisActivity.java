package application_commerciale.coursesnfc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class FavorisActivity extends MenuActivity {
	ListView listeFavoris;
	private List<HashMap<String, String>> produitsFavoris;
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

}
