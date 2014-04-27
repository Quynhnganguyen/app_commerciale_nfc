package application_commerciale.coursesnfc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class AccueilActivity extends MenuActivity {
	private String[] magasins = { "Magasin1", "Magasin2", "Magasin3" };
	Spinner listeMagasins;
	ListView listeProduits;
	List<HashMap<String, String>> produits;
	ListAdapter adapter; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accueil);
		 
		 
		  listeMagasins = (Spinner) findViewById(R.id.listeDeroulanteMagasins);
		  ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
		    android.R.layout.simple_spinner_item, magasins);
		  adapter_state
		    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  listeMagasins.setAdapter(adapter_state);
		  listeMagasins.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			  
		  });
		  
		  listeProduits = (ListView) findViewById(R.id.listeProduits);
		  listeProduits.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			String[][] lesProduits = new String[][] {
					{ "Produit 1", "2€" },
					{ "Produit 2", "3€" },
					{ "Produit 3", "5€" } };

			produits = new ArrayList<HashMap<String, String>>();

			HashMap<String, String> unProduit;

			for (int i = 0; i < lesProduits.length; i++) {

				unProduit = new HashMap<String, String>();

				unProduit.put("text1", lesProduits[i][0]);

				unProduit.put("text2", lesProduits[i][1]);
				produits.add(unProduit);
			}

			 adapter = new SimpleAdapter(this, produits,
					android.R.layout.simple_list_item_2, new String[] { "text1",
							"text2" }, new int[] { android.R.id.text1,
							android.R.id.text2 });
			listeProduits.setAdapter(adapter);

		
		
	}



	

}
