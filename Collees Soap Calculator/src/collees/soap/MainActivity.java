package collees.soap;

import java.util.ArrayList;
import java.util.HashMap;
import collees.soap.R;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	private static String[] oilNames = { "Almond Oil", "Aloe Vera Butter", "Aloe Vera Oil", "Apricot Oil",
		"Avocado Butter", "Avocado Oil", "Babassu Nut Oil", "Beeswax",
		"Borage Oil", "Candelilla Wax", "Canola Oil",
		"Canola Oil, Oleic Acid", "Castor Bean Oil", "Cherry Oil",
		"Chicken Fat", "Cocoa Butter", "Coconut Oil, Refined",
		"Coconut Oil, Hydrogenated", "Coconut Oil, Saturated", "Copha Vegetable Oil",
		"Corn Oil", "Cottonseed Oil", "Crisco Vegetable Oil",
		"Emu Oil", "Evening Primrose Oil", "Flaxseed Oil",
		"Goat Fat", "Goose Fat", "Grapeseed Oil",
		"Hazelnut Oil", "Hempseed Oil", "Jojoba Seed Oil",
		"Jojoba Seed Wax", "Karite Butter", "Kremelta Shortening",
		"Kukui Nut Oil", "Lanolin", "Lard",
		"Linseed Oil", "Macadamia Nut Oil", "Milk Fat",
		"Mink Oil", "Monoi de Tahiti Oil", "Neem Tree Oil",
		"Olive Oil", "Ostrich Oil", "Palm Kernal Oil",
		"Palm Oil", "Peach Kernel Oil", "Peanut Oil",
		"Pumpkin Seed Oil", "Rapeseed Oil", "Rice Bran Oil",
		"Safflower Oil, Linoleic Acid", "Safflower Oil, Oleic Acid", "Sesame Seed Oil",
		"Shea Butter", "Soybean Oil", "Soybean Oil, Hydrogenated",
		"Stearic Acid - Animal", "Stearic Acid - Vegetable", "Sunflower Seed Oil",
		"Sunflower Seed Oil, Oleic Acid", "Tallow, Beef", "Tallow, Deer",
		"Tallow, Sheep", "Tamanu Seed Oil", "Tiare Flower Oil",
		"Walnut Oil", "Wheat Germ Oil"};
	private static Float[] oilNAOH = { .1367f, .1788f, .1421f, .1378f,
		.1339f, .1337f, .1749f, .0689f, 
		.1339f, .0322f, .1328f, .1330f, 
		.1286f, .1389f, .1356f, .1378f, 
		.1910f, .1910f, .2321f, .1910f, 
		.1368f, .1387f, .1369f, .1377f, 
		.1362f, .1358f, .1382f, .1349f, 
		.1321f, .1369f, .1359f, .0695f, 
		.0695f, .1296f, .1910f, .1351f, 
		.0748f, .1399f, .1358f, .1391f, 
		.1599f, .1403f, .1796f, .1372f, 
		.1353f, .1385f, .1777f, .1420f, 
		.1361f, .1367f, .1389f, .1328f, 
		.1284f, .1374f, .1369f, .1336f, 
		.1296f, .1359f, .1361f, .1413f, 
		.1411f, .1358f, .1351f, .1419f, 
		.1382f, .1284f, .1437f, .1796f, 
	    .1349f, .1319f};
	
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
	HashMap oilSapMap;
	TextView text_selectOil;
	TextView text_units;
	
	

	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setVolumeControlStream(AudioManager.STREAM_MUSIC);
	        getPreferences(MODE_PRIVATE);
	        
	        createOilSapMap();
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
	 
	 private void createOilSapMap(){
		 oilSapMap = new HashMap<String, Float>();
		 for(int i = 0; i < oilNames.length; i++){
			 oilSapMap.put(oilNames[i], oilNAOH[i]);
		 }
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
	            	 calculateTotals(rowList);
	             }
	         });
		
		 ll_footer.addView(addButton);
		 ll_footer.addView(removeButton);
		 ll_footer.addView(calculateButton);
		 
	        
	 }
	 
	 private void calculateTotals(ArrayList<LinearLayout>rowList){
		 float totalOil = calculateTotalOil(rowList);
		 float totalNAOH = calculateTotalNAOH(rowList, totalOil);
		 float totalLye = calculateTotalLye(totalOil, totalNAOH);
		 float totalLyeWater = calculateTotalLyeWater(totalLye);
		 float totalWater = calculateTotalWater(totalLyeWater, totalLye);
		 float totalSoap = calculateTotalSoap(totalOil, totalLyeWater);
		 
		 Log.d(TAG, "totalOil = " + totalOil);
		 Log.d(TAG, "totalNAOH = " + totalNAOH);
		 Log.d(TAG, "totalLYE = " + totalLye);
		 Log.d(TAG, "totalLyeWater = " + totalLyeWater);
		 Log.d(TAG, "totalWater= " + totalWater);
		 Log.d(TAG, "totalSoap= " + totalSoap);
		 
		 Intent intent = new Intent(MainActivity.this, ResultActivity.class);
		 Bundle b = new Bundle();

		 b.putFloat("totalOil", totalOil);
		 b.putFloat("totalLye", totalLye);
		 b.putFloat("totalWater", totalWater);
		 b.putFloat("totalSoap", totalSoap);

		 intent.putExtras(b);

		 startActivity(intent);

		 finish();
	 }
	 
	 private float calculateTotalSoap(float totalOil, float totalLyeWater){
		 return (totalOil + totalLyeWater);
	 }
	 
	 private float calculateTotalWater(float totalLyeWater, float totalLye){
		 return (totalLyeWater - totalLye);
	 }
	 
	 private float calculateTotalLyeWater(float totalLye)
	 {
		 return (totalLye / 0.3f);
	 }
	 
	 private float calculateTotalLye(float totalOil, float totalNAOH){
		return (totalOil * totalNAOH);
	 }
	 
	 private float calculateTotalNAOH(ArrayList<LinearLayout>rowList, float totalOil){
		// Log.d(TAG, "TotalOil = " + totalOil);
		 float totalNAOH = 0;
		 for(int i = 1; i < rowList.size(); i++){
			 ((Spinner)rowList.get(i).getChildAt(0)).getItemAtPosition(0).toString();
			// Log.d(TAG, "NAOH = " + ((Spinner)rowList.get(i).getChildAt(0)).getItemAtPosition(((Spinner)rowList.get(i).getChildAt(0)).getSelectedItemPosition()));
			 float oilAmount = Float.parseFloat(((EditText) rowList.get(i).getChildAt(1)).getText().toString());
			 String oilName = (String) ((Spinner)rowList.get(i).getChildAt(0)).getItemAtPosition(((Spinner)rowList.get(i).getChildAt(0)).getSelectedItemPosition());
			 float naohFactor = (Float) oilSapMap.get(oilName);
			
			 float percentageSolution = oilAmount / totalOil;
			 float thisNAOH = percentageSolution * naohFactor;
			 totalNAOH += thisNAOH;
			 /*
			 Log.d(TAG, "OilName = " + oilName);
			 Log.d(TAG, "naohFactor = " + naohFactor);
			 Log.d(TAG, "OilAmount = " + oilAmount);
			 Log.d(TAG, "Percentage = " + percentageSolution);
			 Log.d(TAG, "ThisNAOH = " + thisNAOH);
			 */
			 
			 
			
		 }
		 
		 //Log.d(TAG, "TotalNAOH = " + totalNAOH);
		 return totalNAOH;
	 }
	 
	 private float calculateTotalOil(ArrayList<LinearLayout>rowList){
		 float totalOil = 0;
		 
		 for(int i =1; i < rowList.size()-0;i++){
			 //Log.d(TAG, "gettext = " + ((EditText) rowList.get(i).getChildAt(1)).getText().toString());
			 //totalOil += Integer.getInteger(((EditText) rowList.get(i).getChildAt(1)).getText().toString());
			 Float amount = new Float(0f);
			 amount = Float.parseFloat(((EditText) rowList.get(i).getChildAt(1)).getText().toString());
			 totalOil += amount;
			 
		 }
		 
		 
		 return totalOil;
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
