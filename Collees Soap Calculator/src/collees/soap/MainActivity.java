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
	
	private MediaPlayer mp;
	private SharedPreferences preferences;
	
	private static final int STARTING_POSITION = 0;
	private static final String CURRENT_POSITION = "currentPosition";
	private static final String TAG = "CS";
	
	ImageView cowImage;
	
	private static int[] pictureIds = { R.drawable.bear, R.drawable.cat};
 
	private static int[] soundIds = { R.raw.bear, R.raw.cat};
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setVolumeControlStream(AudioManager.STREAM_MUSIC);
	        preferences = getPreferences(MODE_PRIVATE);

	        setStartingPosition();
	        
	        setContentView(R.layout.main);
	        cowImage = (ImageView) this.findViewById(R.id.mycow);
	        cowImage.setOnClickListener(this);
	        
	        cowImage.setImageDrawable(getDrawable(getCurrentPosition()));
	        playCurrentSound();
	        
	    }
	 
	 private void setStartingPosition() {
	        SharedPreferences.Editor editor = preferences.edit();
	        editor.putInt(CURRENT_POSITION, STARTING_POSITION);
	        editor.commit();
	    }
	 
	 private Drawable getDrawable(int index) {
		    return getResources().getDrawable(pictureIds[index]);
		}
	 
	 @Override
	 public void onClick(View v) {
		 Log.d(TAG, "Clicked !" + v.getBaseline());
	     updateCurrentPosition(1);
	     cowImage.setImageDrawable(getDrawable(getCurrentPosition()));
	     playCurrentSound();
	     
	 }
	 
	 private void playCurrentSound(){
		 if (mp != null) {
	         mp.release();
	     }
		 int currentPosition = preferences.getInt(CURRENT_POSITION, 0);
		 mp = MediaPlayer.create(this, soundIds[currentPosition]);
	     mp.start();
	 }
	 
	 private int getCurrentPosition() {
	        return preferences.getInt(CURRENT_POSITION, 0);
	    }
	 
	    private void updateCurrentPosition(int delta) {
	        int currentPosition = getCurrentPosition();
	        int updatedPosition = (pictureIds.length + currentPosition + delta) % pictureIds.length;
	        Log.d(TAG, "Moving position from " + currentPosition + " to " + updatedPosition);
	        Editor editor = preferences.edit();
	        editor.putInt(CURRENT_POSITION, updatedPosition);
	        editor.commit();
	    }
	 
	 @Override
		protected void onPause() {
			super.onPause();
			if (mp != null) {
				mp.release();
			}
		}
	 
	
	 
	 

}
