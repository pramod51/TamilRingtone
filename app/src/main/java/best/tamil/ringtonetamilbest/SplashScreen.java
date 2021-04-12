package best.tamil.ringtonetamilbest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.HashMap;

import best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest;

import static best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest.CHILD_KEY;
import static best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest.KEY_ID;
import static best.tamil.ringtonetamilbest.SQLiteDB.DBHelperLatest.TABEL_NAME;

public class SplashScreen extends AppCompatActivity {
    StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("Latest");
    DBHelperLatest dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        dbHelper=new DBHelperLatest(this);
        //To add data in realtime Latest child from storage
        /*storageRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {



                for (final StorageReference item : listResult.getItems()) {
                    // All the items under listRef.
                    final HashMap<String, Object> map=new HashMap<>();
                    map.put("Title",item.getName().substring(0,item.getName().indexOf('.')));
                    item.getMetadata().addOnSuccessListener(new OnSuccessListener<StorageMetadata>() {
                        @Override
                        public void onSuccess(StorageMetadata storageMetadata) {
                            Log.v("tag","Size=="+storageMetadata.getSizeBytes());
                            map.put("Size",storageMetadata.getSizeBytes()/1024);
                            item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Log.v("tag","Url=="+uri.toString());
                                    map.put("Url",uri.toString());
                                    MediaPlayer mediaPlayer =new MediaPlayer();
                                    try {
                                        mediaPlayer.setDataSource(uri.toString());
                                        mediaPlayer.prepare();

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    map.put("Duration",mediaPlayer.getDuration()/1000);
                                    map.put("DownloadCount",0);
                                    map.put("Ratings",0);
                                    FirebaseDatabase.getInstance().getReference().child("App").child("Latest").push().setValue(map);
                                }
                            });
                        }
                    });
                    Log.v("tag","item"+item.getName()+" "+item.getDownloadUrl()+" "+item.getName());


                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("tag",e.toString());
            }
        });*/

        FirebaseDatabase.getInstance().getReference().child("App").child("Latest")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        SQLiteDatabase db=dbHelper.getReadableDatabase();
                        String query = "SELECT * FROM "+ TABEL_NAME;

                        for (DataSnapshot ds:snapshot.getChildren()){
                            Cursor cursor = db.rawQuery(query,null);
                            boolean isThere=false;
                            while (cursor.moveToNext()){
                                if (cursor.getString(cursor.getColumnIndex(CHILD_KEY)).equals(ds.getKey())) {
                                    isThere=true;
                                    break;
                                }
                            }
                            if (!isThere)
                            dbHelper.insertLatestRingtone(ds.getKey(),ds.child("Title").getValue(String.class),String.valueOf(ds.child("Duration").getValue(Integer.class)),ds.child("Url").getValue(String.class),
                                    String.valueOf(ds.child("DownloadCount").getValue(Integer.class)),"No",String.valueOf(ds.child("Size").getValue(Integer.class))
                            ,String.valueOf(ds.child("Ratings").getValue(Integer.class)));

                        }
                        startActivity(new Intent(SplashScreen.this,MainActivity.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





    }
}