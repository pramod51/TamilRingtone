package best.tamil.ringtonetamilbest.ui.Latest;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import best.tamil.ringtonetamilbest.R;
import best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest;

import static best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest.CHILD_KEY;
import static best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest.DOWNLOAD_COUNT;
import static best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest.DURATION;
import static best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest.RATINGS;
import static best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest.SIZE;
import static best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest.TABEL_NAME;
import static best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest.TITLE;
import static best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest.URL;

public class PapularFragment extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<LatestModel> arrayList;
    private RecyclerView.Adapter mAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_latest, container, false);
        recyclerView=root.findViewById(R.id.recycler_view);

        arrayList=new ArrayList<>();
        DBHelperLatest dbHelper=new DBHelperLatest(getContext());
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String query = "SELECT * FROM "+ TABEL_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            arrayList.add(new LatestModel(cursor.getString(cursor.getColumnIndex(TITLE)),cursor.getString(cursor.getColumnIndex(DURATION)),cursor.getString(cursor.getColumnIndex(URL)),
                    cursor.getString(cursor.getColumnIndex(RATINGS)),cursor.getString(cursor.getColumnIndex(CHILD_KEY)),cursor.getString(cursor.getColumnIndex(DOWNLOAD_COUNT)),
                    cursor.getString(cursor.getColumnIndex(SIZE)),false,false));
        }



        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter=new LatestAdapter(arrayList,getContext());
        recyclerView.setAdapter(mAdapter);




        /*MediaPlayer mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/besttamilringtones-7e13e.appspot.com/o/Latest%2FWhatsApp%20Audio%202021-04-07%20at%208.42.24%20PM?alt=media&token=cb0c36d8-da50-430c-ac15-5d4e6179e0c1");
            Log.v("tag","loading");
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("tag",e.toString());
        }
        try {
            mediaPlayer.prepare();
            Log.v("tag","preparing");
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("tag","prepare -->"+e.toString());
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }

        });
        mediaPlayer.start();*/


        return root;
    }
}