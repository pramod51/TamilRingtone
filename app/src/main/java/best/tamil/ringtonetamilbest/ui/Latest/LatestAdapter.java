package best.tamil.ringtonetamilbest.ui.Latest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.ohoussein.playpause.PlayPauseView;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.io.IOException;
import java.util.ArrayList;

import best.tamil.ringtonetamilbest.R;
import best.tamil.ringtonetamilbest.ui.RingtoneDetails.RingtoneDetails;

public class LatestAdapter extends RecyclerView.Adapter<LatestAdapter.ViewHolder> {

    private ArrayList<LatestModel> arrayList;
    private Context context;
    boolean isStop=true;
    public static final String INTENT_KEY="intentKey";
    MediaPlayer mediaPlayer=null;
    ArrayList<String> dataList;
    int pos=-1;
    private String[] cardColors={"FF018786","8A2BE2","DC143C","808080","DAA520"
            ,"EE3030","24E364","40E0D0"};

    public LatestAdapter(ArrayList<LatestModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        isStop=true;
        final LatestModel latestModel=arrayList.get(position);
        holder.title.setText(latestModel.getTitle());
        holder.duration.setText(latestModel.getDuration()+" s");
        holder.cardView.setCardBackgroundColor(Color.parseColor("#"+cardColors[position%cardColors.length]));
        holder.downloads.setText(latestModel.getDownloadCount());
        if (pos!=-1&&arrayList.get(pos).isPlaying) {
            holder.playPauseView.toggle();
            Log.v("tag","ifpos=="+pos);
            arrayList.get(pos).setPlaying(false);
        }
        else {
            Log.v("tag","elspos=="+pos);
        }
        holder.playPauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("tag",pos+"Position=="+position);

                if (position!=pos){
                    if (mediaPlayer!=null)
                        mediaPlayer.stop();
                    mediaPlayer=null;
                    if (pos!=-1){
                        notifyItemChanged(pos);
                    }
                }
                pos=position;
                holder.playPauseView.toggle();
                if (holder.playPauseView.isPlay()&& mediaPlayer !=null) {
                    Log.v("tag","Pause");
                    mediaPlayer.pause();
                    latestModel.setPlaying(false);
                } else if (!holder.playPauseView.isPlay()){
                    if (mediaPlayer !=null) {
                        mediaPlayer.start();
                        Log.v("tag","Resuming");
                        latestModel.setPlaying(true);
                    }
                    else {
                        holder.progressBar.setVisibility(View.VISIBLE);
                        mediaPlayer =new MediaPlayer();
                        try {
                            mediaPlayer.setDataSource(latestModel.getRingtoneUrl());
                            Log.v("tag","loading");
                            mediaPlayer.prepareAsync();
                            Log.v("tag","preparing");
                            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                @Override
                                public void onPrepared(MediaPlayer mediaPlayer) {
                                    mediaPlayer.start();
                                    Log.v("tag","playing");
                                    isStop=false;
                                    latestModel.setPlaying(true);
                                    holder.progressBar.setVisibility(View.GONE);
                                }
                            });
                            mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.v("tag",e.toString());
                        }
                    }


                }
            }
        });
        if (mediaPlayer!=null){
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataList=new ArrayList<>();
                dataList.add(latestModel.getTitle());
                dataList.add(latestModel.getRingtoneUrl());
                dataList.add(latestModel.getSize());
                dataList.add(latestModel.getDownloadCount());
                dataList.add(latestModel.getChildKay());
                dataList.add(latestModel.getRating());
                if (latestModel.isFavourite)
                    dataList.add("YES");
                else
                    dataList.add("NO");
                Intent intent=new Intent(context,RingtoneDetails.class);
                intent.putStringArrayListExtra(INTENT_KEY,dataList);
                context.startActivity(intent);
                    /*FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_right);
                    transaction.addToBackStack(null);
                    transaction.add(R.id.fragment_container, new RingtoneDetails(), "BLANK_FRAGMENT").commit();*/
            }
        });

    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,duration,downloads;
        private PlayPauseView playPauseView;
        private ShineButton shineButton;
        private ProgressBar progressBar;
        private CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.ringtone_title);
            duration=itemView.findViewById(R.id.duration);
            playPauseView=itemView.findViewById(R.id.play_pause_view);
            shineButton=itemView.findViewById(R.id.shine_button);
            progressBar=itemView.findViewById(R.id.circular_progress_bar);
            cardView=itemView.findViewById(R.id.card_view);
            downloads=itemView.findViewById(R.id.downloads);


        }
    }
}
