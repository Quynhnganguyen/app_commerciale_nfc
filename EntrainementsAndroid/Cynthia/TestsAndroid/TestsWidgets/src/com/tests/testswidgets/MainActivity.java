package com.tests.testswidgets;

import android.os.Bundle;
import android.app.Activity;
import android.text.InputType;
import android.view.Menu;
import android.widget.Button;
import android.widget.*;

import com.tests.testswidgets.R;

public class MainActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		EditText editText = new EditText(this);
		editText.setHint("Text");
		
		editText.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		editText.setLines(5);
		
		Button button1 = new Button(this);
		button1.setText(R.string.button1);
		
		CheckBox checkBox1 = new CheckBox(this);
		checkBox1.setText("Ok");
		checkBox1.setChecked(true);
		
		RadioGroup rg = new RadioGroup(this);
		RadioButton  rb1 = new RadioButton(this);
		rb1.setText(R.string.rb1);
		RadioButton  rb2 = new RadioButton(this);
		rb2.setText(R.string.rb2);
		RadioButton  rb3 = new RadioButton(this);
		rb3.setText(R.string.rb3);
		RadioButton  rb4 = new RadioButton(this);
		rb4.setText(R.string.rb4);
		
		rg.addView(rb1,0);
		rg.addView(rb2,1);
		rg.addView(rb3,2);
		rg.addView(rb4,3);
		
		rg.check(0);
		
		int solution = rg.getCheckedRadioButtonId();
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
