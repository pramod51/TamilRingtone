package best.tamil.ringtonetamilbest.ui.RingtoneDetails;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import best.tamil.ringtonetamilbest.R;
import co.mobiwise.library.MusicPlayerView;

import static best.tamil.ringtonetamilbest.ui.Latest.LatestAdapter.INTENT_KEY;

public class RingtoneDetails extends AppCompatActivity {

    MusicPlayerView mpv;

   /* @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_ringtone_details, container, false);
        */
    private String title,url,size,downloads,childKey,rating,isFav;
    private MediaPlayer mediaPlayer=null;
    private boolean isPrepaired=false,isPlaying=false;
    private long startTime=0;
    private TextView sizeText,downloadText,durationText;
    private CountDownTimer countDownTimer;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.fragment_ringtone_details);
        mpv = (MusicPlayerView) findViewById(R.id.mpv);
        sizeText=findViewById(R.id.size);
       downloadText=findViewById(R.id.downloads);
       durationText=findViewById(R.id.duration);



        /*mpv.setButtonColor(Color.DKGRAY);
        mpv.setCoverDrawable(R.drawable.mycover);*/
        mpv.setProgressEmptyColor(Color.GRAY);
        mpv.setProgressLoadedColor(Color.BLUE);
        mpv.setTimeColor(Color.WHITE);
        mpv.setVelocity(2);
       ArrayList<String> datList=getIntent().getStringArrayListExtra(INTENT_KEY);
       title=datList.get(0);
       url=datList.get(1);
       size=datList.get(2);
       downloads=datList.get(3);
       childKey=datList.get(4);
       rating=datList.get(5);
       isFav=datList.get(6);
       downloadText.setText(downloads);;
       sizeText.setText(size+"KB");
       durationText.setText("not updated");
       mediaPlayer=new MediaPlayer();
       Log.v("tag",url);
       try {
           mediaPlayer.setDataSource(url);
           mediaPlayer.prepareAsync();
       } catch (IOException e) {
           e.printStackTrace();
       }
       mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           @Override
           public void onPrepared(MediaPlayer mediaPlayer) {
               isPrepaired=true;
               Log.v("tag","Prepared");
               startTime=mediaPlayer.getDuration();
               mpv.setMax(mediaPlayer.getDuration()/1000);
           }
       });
       mpv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Log.v("tag","Clicked");
               if (isPrepaired){
                   mpv.toggle();
                   if (isPlaying){
                       mediaPlayer.pause();
                       isPlaying=false;
                       countDownTimer.cancel();
                       Log.v("tag","paused");
                   }
                   else if(mediaPlayer!=null){
                       mediaPlayer.start();
                       setProgress(startTime);
                       isPlaying=true;
                       Log.v("tag","playing");
                   }
               }

           }
       });
       mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           public void onCompletion(MediaPlayer mediaPlayer) {
               mediaPlayer.start();
               startTime=mediaPlayer.getDuration();
               setProgress(startTime);
           }
       });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mediaPlayer!=null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }

    }
    private void setProgress(final long start){

        countDownTimer=new CountDownTimer(start,1000) {
            @Override
            public void onTick(long l) {
                mpv.setProgress((int) ((mediaPlayer.getDuration()/1000)-(l/1000)));
                startTime=l;
                Log.v("tag","--->"+((mediaPlayer.getDuration()/1000)-(l/1000)));
            }

            @Override
            public void onFinish() {

            }
        };
        countDownTimer.start();

    }
}