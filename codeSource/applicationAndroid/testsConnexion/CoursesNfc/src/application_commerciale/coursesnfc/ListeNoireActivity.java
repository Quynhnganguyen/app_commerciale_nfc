package application_commerciale.coursesnfc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ListeNoireActivity extends MenuActivity {
	ListView listeNoire;
	List<HashMap<String, String>> produitsNoirs;
	ListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_liste_noire);
		listeNoire = (ListView) findViewById(R.id.listeNoire);
		listeNoire.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			String[][] lesProduitsNoirs = new String[][] {
					{ "Produit 1", "Magasin 1" },
					{ "Produit 2", "Magasin 1"},
					{ "Produit 3", "Magasin 2" } };

			produitsNoirs = new ArrayList<HashMap<String, String>>();

			HashMap<String, String> unProduitNoir;

			for (int i = 0; i < lesProduitsNoirs.length; i++) {

				unProduitNoir = new HashMap<String, String>();

				unProduitNoir.put("text1", lesProduitsNoirs[i][0]);

				unProduitNoir.put("text2", lesProduitsNoirs[i][1]);
				produitsNoirs.add(unProduitNoir);
			}

			 adapter = new SimpleAdapter(this, produitsNoirs,
					android.R.layout.simple_list_item_2, new String[] { "text1",
							"text2" }, new int[] { android.R.id.text1,
							android.R.id.text2 });
			listeNoire.setAdapter(adapter);
		
	}

}
