package collees.soap;

import java.util.ArrayList;

import collees.soap.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup.LayoutParams;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	private SharedPreferences preferences;
	private String TAG = "SOAP";
	private int numRows;
	ScrollView sv;
	LinearLayout ll_display;
	LinearLayout ll_header;
	LinearLayout ll_main;
	LinearLayout ll_footer;
	LinearLayout ll_row1;
	LinearLayout ll_row2;
	ArrayList<LinearLayout>rowList;
	TextView text_selectOil;
	TextView text_units;
	
	

	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setVolumeControlStream(AudioManager.STREAM_MUSIC);
	        preferences = getPreferences(MODE_PRIVATE);
	       // setContentView(R.layout.main);
	        
			createWindow();
	        
			/*
	        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
	        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	                this, R.array.oils_array, android.R.layout.simple_spinner_item);
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner.setAdapter(adapter);
	        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
	        */

	        
	    }
	 
	 private void addNewRow(){
		 LinearLayout newRow = new LinearLayout(this);
		 newRow.setOrientation(LinearLayout.HORIZONTAL);
		 rowList.add(newRow);
		 ll_main.addView(newRow);
		 
	 }
	 
	 private void createWindow(){
		 numRows = 2;
		 
		 rowList = new ArrayList<LinearLayout>();
		 sv = new ScrollView(this);
		 
		 ll_display = new LinearLayout(this);
		 ll_display.setOrientation(LinearLayout.VERTICAL);
		 ll_display.setPadding(10, 10, 10, 10);
		 
		 ll_header = new LinearLayout(this);
		 ll_header.setOrientation(LinearLayout.HORIZONTAL);
		 ll_header.setPadding(10, 10, 10, 10);
		 
		 ll_footer = new LinearLayout(this);
		 ll_footer.setOrientation(LinearLayout.HORIZONTAL);
		 ll_footer.setPadding(10, 10, 10, 10);
		 
		 ll_main = new LinearLayout(this);
		 ll_main.setOrientation(LinearLayout.VERTICAL);
		 ll_main.setPadding(10, 10, 10, 10);
			
			for(int i = 0; i < numRows; i++)
			{
				addNewRow();
			}
			/*
			rowList.add(new LinearLayout(this));
			ll_row1 = new LinearLayout(this);
			ll_row1.setOrientation(LinearLayout.HORIZONTAL);
			
			ll_row2 = new LinearLayout(this);
			ll_row2.setOrientation(LinearLayout.HORIZONTAL);
			
			ll_main.addView(ll_row1);
			ll_main.addView(ll_row2);
			*/
			
			createHeader(0);
			addOilSelector(1);
			createFooter();
			
			ll_display.addView(ll_header);
			ll_display.addView(ll_main);
			ll_display.addView(ll_footer);
			sv.addView(ll_display);
			sv.setBackgroundDrawable(getResources().getDrawable(R.drawable.background1));
			this.setContentView(sv);
	 }
	 
	 private void moveFooter(){
		 rowList.set(rowList.size()-1, rowList.get(rowList.size()-2));
		 LinearLayout tempLast = rowList.get(rowList.size()-1);
		 LinearLayout tempFooter = rowList.get(rowList.size()-2);
		 rowList.set(rowList.size()-1, tempFooter);
		 rowList.set(rowList.size()-2, tempLast);
	 }
	 
	 private void createFooter(){
		 
		 Button addButton = new Button(this);
		 addButton.setText("Add");
		 addButton.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	                 addNewRow();
	                 
	                 addOilSelector(rowList.size()-1);
	                // moveFooter();
	                 
	             }
	         });

	        
	       
		 
		 Button removeButton = new Button(this);
		 removeButton.setText("Remove");
		 removeButton.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	            	 if(rowList.size()>1){
	            	removeOilSelector(rowList.size()-1);
	            	 }
	                 
	             }
	         });
		 
		 Button calculateButton = new Button(this);
		 calculateButton.setText("Calculate");
		 calculateButton.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View v) {
	            	 
	                 
	             }
	         });
		
		 ll_footer.addView(addButton);
		 ll_footer.addView(removeButton);
		 ll_footer.addView(calculateButton);
		 
	        
	 }
	 
	 private void removeOilSelector(int rowNum){
		 ll_main.removeView(rowList.remove(rowNum));
		 //rowList.remove(rowNum);
		 //rowList.
	 }
	 
	 private void addOilSelector(int rowNum){
		 Spinner spinner = new Spinner(this);
	        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	                this, R.array.oils_array, android.R.layout.simple_spinner_item);
	        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        spinner.setAdapter(adapter);
	        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
	        rowList.get(rowNum).addView(spinner);
	        
	        EditText numUnits = new EditText(this);
	        numUnits.setPadding(15,	 0, 0, 0);
	        numUnits.setInputType(3);
	        numUnits.setWidth(50);
	        
	        rowList.get(rowNum).addView(numUnits);
	        
	       
	        
	 }
	 
	private void createHeader(int rowNum){
		text_selectOil = new TextView(this);
		text_selectOil.setPadding(15, 0, 0, 0);
		text_selectOil.setText("Select Oils");
		
		text_units = new TextView(this);
		
		text_units.setPadding(50, 0, 0, 0);
		text_units.setText("Units");
		
		ll_header.addView(text_selectOil);
		
		ll_header.addView(text_units);
	}
	 @Override
	 public void onClick(View v) {
		 Log.d(TAG, "Clicked !" + v.getBaseline());
	   
	     
	 }
 
	 @Override
		protected void onPause() {
			super.onPause();
			
		}
	 
	 public class MyOnItemSelectedListener implements OnItemSelectedListener {

		    public void onItemSelected(AdapterView<?> parent,
		        View view, int pos, long id) {
		      Toast.makeText(parent.getContext(),
		          parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
		    }

		    public void onNothingSelected(AdapterView parent) {
		      // Do nothing.
		    }
		}

}
