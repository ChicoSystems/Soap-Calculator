package collees.soap;

import collees.soap.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener{
	
	private SharedPreferences preferences;
	private String TAG = "SOAP";
	

	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setVolumeControlStream(AudioManager.STREAM_MUSIC);
	        preferences = getPreferences(MODE_PRIVATE);
	        setContentView(R.layout.main);
	        
	    }
	 
	
	 @Override
	 public void onClick(View v) {
		 Log.d(TAG, "Clicked !" + v.getBaseline());
	   
	     
	 }
 
	 @Override
		protected void onPause() {
			super.onPause();
			
		}

}
