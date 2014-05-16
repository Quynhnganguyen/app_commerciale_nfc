package application_commerciale.coursesnfc;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import application_commerciale.coursesnfc.bdd.Magasins;
import application_commerciale.coursesnfc.bdd.WebService;

public class TestWSActivity extends Activity {
	TextView test;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		WebService webService = new WebService();
		List<Magasins> liste = webService.getMagasins();
		test.setText(liste.get(1).getMagasin_nom());
		test = (TextView) findViewById(R.id.test1);

	}

}
