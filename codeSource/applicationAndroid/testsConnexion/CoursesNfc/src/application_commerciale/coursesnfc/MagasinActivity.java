package application_commerciale.coursesnfc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MagasinActivity extends MenuActivity {
	ListView listeMagasins;
	List<HashMap<String, String>> magasins;
	ListAdapter adapter; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_magasin);
		
		 listeMagasins = (ListView) findViewById(R.id.listeMagasins);
		  listeMagasins.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			String[][] lesMagasins = new String[][] {
					{ "Magasin 1", "26 Place de la Nation 75012 Paris" },
					{ "Magasin 2", "20 Rue du Chemin Vert 75011 Paris" },
					{ "Magasin 3", "3 Rue de la Station 92600 Asnières-sur-Seine" } };

			magasins = new ArrayList<HashMap<String, String>>();

			HashMap<String, String> unMagasin;

			for (int i = 0; i < lesMagasins.length; i++) {

				unMagasin = new HashMap<String, String>();

				unMagasin.put("text1", lesMagasins[i][0]);

				unMagasin.put("text2", lesMagasins[i][1]);
				magasins.add(unMagasin);
			}

			 adapter = new SimpleAdapter(this, magasins,
					android.R.layout.simple_list_item_2, new String[] { "text1",
							"text2" }, new int[] { android.R.id.text1,
							android.R.id.text2 });
			listeMagasins.setAdapter(adapter);
		
	}

}
