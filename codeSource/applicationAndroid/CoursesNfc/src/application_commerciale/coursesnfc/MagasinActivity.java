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

		magasins = new ArrayList<HashMap<String, String>>();

			HashMap<String, String> unMagasin;

		
		String[][] lesMagasins = new String[][]

		HttpClient httpclient = new DefaultHttpClient();
			try {
				HttpGet httpGet = new HttpGet(
						"http://quiet-wildwood-3463.herokuapp.com/magasins/index?action=get");
				HttpResponse httpresponse = httpclient.execute(httpGet);
				HttpEntity httpentity = httpresponse.getEntity();
				if (httpentity != null) {
					InputStream inputstream = httpentity.getContent();
					BufferedReader bufferedreader = new BufferedReader(
							new InputStreamReader(inputstream));
					StringBuilder strinbulder = new StringBuilder();
					String ligne = bufferedreader.readLine();
					while (ligne != null) {
						strinbulder.append(ligne + "n");
						ligne = bufferedreader.readLine();
					}
					bufferedreader.close();
					
					JSONArray ja = new JSONArray(strinbulder.toString());
					List<String> allNames = new ArrayList<String>();
					for (int i =0;i<ja.length();i++){
						JSONObject actor = ja.optJSONObject(i);
						String name = actor.getString("magasin_nom");
						String adresse = actor.getString("magasin_adresse");
	
						unMagasin = new HashMap<String, String>();

						unMagasin.put("text1", name);

						unMagasin.put("text2", adresse);
						magasins.add(unMagasin);
					}
					
				}
			} catch (Exception e) {
				Log.e(TAG, e.getMessage());
	}

			 adapter = new SimpleAdapter(this, magasins,
					android.R.layout.simple_list_item_2, new String[] { "text1",
							"text2" }, new int[] { android.R.id.text1,
							android.R.id.text2 });
			listeMagasins.setAdapter(adapter);
		
	}

}
