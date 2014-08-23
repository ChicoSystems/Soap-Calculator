package collees.soap;

import java.text.DecimalFormat;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends Activity{
	
	
	private LinearLayout ll_display;
	private LinearLayout ll_header;
	private LinearLayout ll_totalOil;
	private LinearLayout ll_totalLye;
	private LinearLayout ll_totalWater;
	private LinearLayout ll_totalSoap;
	private LinearLayout ll_footer;
	Bundle b;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.result);
        
        b = getIntent().getExtras();

       
        float totalLye = b.getFloat("totalLye", 0);
        float totalWater = b.getFloat("totalWater", 0);
        float totalSoap = b.getFloat("totalSoap", 0);
       
       
       // Log.d("RESULTACTIVITY", "totalOil = " + totalOil);
        Log.d("RESULTACTIVITY", "totalLye = " + totalLye);
        Log.d("RESULTACTIVITY", "totalWater = " + totalWater);
        Log.d("RESULTACTIVITY", "totalSoap = " + totalSoap);
        createWindow();
        
 
    }
 
    private void createWindow(){
    	DecimalFormat df = new DecimalFormat("###.##");

    	 ll_display = new LinearLayout(this);
		 ll_display.setOrientation(LinearLayout.VERTICAL);
		 ll_display.setPadding(10, 0, 10, 0);
		 
		 ll_header = new LinearLayout(this);
		 ll_header.setOrientation(LinearLayout.HORIZONTAL);
		 ll_header.setPadding(10, 0, 10, 0);
		 
		 ll_totalOil = new LinearLayout(this);
		 ll_totalOil.setOrientation(LinearLayout.HORIZONTAL);
		 ll_totalOil.setPadding(10, 10, 10, 10);
		 
		 ll_totalLye = new LinearLayout(this);
		 ll_totalLye.setOrientation(LinearLayout.HORIZONTAL);
		 ll_totalLye.setPadding(10, 10, 10, 10);
		 
		 ll_totalWater = new LinearLayout(this);
		 ll_totalWater.setOrientation(LinearLayout.HORIZONTAL);
		 ll_totalWater.setPadding(10, 10, 10, 10);
		 
		 ll_totalSoap = new LinearLayout(this);
		 ll_totalSoap.setOrientation(LinearLayout.HORIZONTAL);
		 ll_totalSoap.setPadding(10, 10, 10, 10);
		 
		 ll_footer = new LinearLayout(this);
		 ll_footer.setOrientation(LinearLayout.HORIZONTAL);
		 ll_footer.setPadding(10, 0, 10, 0);
		 
		 
		 
		 TextView headerLabel = new TextView(this);
		 headerLabel.setTextSize(25);
		 headerLabel.setTextColor(Color.BLUE);
		 headerLabel.setText("Results: ");
		 ll_header.addView(headerLabel);
		 ll_display.addView(ll_header);
		 
		 
		 TextView totalOilLabel = new TextView(this);
		 totalOilLabel.setTextSize(30);
		 totalOilLabel.setText("Total Oil: ");
		 
		 TextView totalOil = new TextView(this);
		 totalOil.setTextSize(30);
		 totalOil.setTextColor(Color.RED);
		 totalOil.setText((df.format(b.getFloat("totalOil", 0))));
		 
		 ll_totalOil.addView(totalOilLabel);
		 ll_totalOil.addView(totalOil);
		 
		 ll_display.addView(ll_totalOil);
		 
		 
		 TextView totalLyeLabel = new TextView(this);
		 totalLyeLabel.setTextSize(30);
		 totalLyeLabel.setText("Total Lye: ");
		 
		 TextView totalLye = new TextView(this);
		 totalLye.setTextSize(30);
		 totalLye.setTextColor(Color.RED);
		 totalLye.setText((df.format(b.getFloat("totalLye", 0))));
		 
		 ll_totalLye.addView(totalLyeLabel);
		 ll_totalLye.addView(totalLye);
		 ll_display.addView(ll_totalLye);
		 
		 TextView totalWaterLabel = new TextView(this);
		 totalWaterLabel.setTextSize(30);
		 totalWaterLabel.setText("Total Water: ");
		 
		 TextView totalWater = new TextView(this);
		 totalWater.setTextSize(30);
		 totalWater.setTextColor(Color.RED);
		 totalWater.setText((df.format(b.getFloat("totalWater", 0))));
		 
		 ll_totalWater.addView(totalWaterLabel);
		 ll_totalWater.addView(totalWater);
		 ll_display.addView(ll_totalWater);
		 
		 TextView totalSoapLabel = new TextView(this);
		 totalSoapLabel.setTextSize(30);
		 totalSoapLabel.setText("Total Soap: ");
		 
		 TextView totalSoap = new TextView(this);
		 totalSoap.setTextSize(30);
		 totalSoap.setTextColor(Color.RED);
		 totalSoap.setText((df.format(b.getFloat("totalSoap", 0))));
		 
		 ll_totalSoap.addView(totalSoapLabel);
		 ll_totalSoap.addView(totalSoap);
		 ll_display.addView(ll_totalSoap);
		 
		 TextView footerLabel = new TextView(this);
		 footerLabel.setTextSize(10);
		 footerLabel.setTextColor(Color.GRAY);
		 footerLabel.setText(" Created by www.ChicoSystems.com ");
		 ll_footer.addView(footerLabel);
		 ll_display.addView(ll_footer);
		 
		 setContentView(ll_display);
		  
		  
    }

 
  
}