package best.tamil.ringtonetamilbest.SQLiteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelperLatest extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bestTamilRingtone1.db";
    public static final String URL = "url";
    public static final String TABEL_NAME = "tablName";
    public static final String TITLE = "title";
    public static final String KEY_ID = "id";
    public static final String SIZE = "size";
    public static final String CHILD_KEY = "childKey";
    public static final String DURATION = "duration";
    public static final String DOWNLOAD_COUNT = "downloadCount";
    public static final String RATINGS = "ratings";
    public static final String IS_FAVORIT = "isFav";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABEL_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY," +
                    TITLE + " TEXT," +
                    RATINGS + " TEXT," +
                    SIZE + " TEXT," +
                    DURATION + " TEXT," +
                    CHILD_KEY + " TEXT," +
                    URL + " TEXT," +
                    DOWNLOAD_COUNT + " TEXT," +
                    IS_FAVORIT + " TEXT)";

    public DBHelperLatest(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if exist        
        db.execSQL("DROP TABLE IF EXISTS "+TABEL_NAME);
        //Create tables again        
        onCreate(db);

    }

    public void insertLatestRingtone(String childKey,String title, String duration, String url, String downloadCount, String isFav,String size,String rating) {
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(TITLE,title);
        cValues.put(DURATION,duration);
        cValues.put(URL,url);
        cValues.put(DOWNLOAD_COUNT,downloadCount);
        cValues.put(IS_FAVORIT,isFav);
        cValues.put(CHILD_KEY,childKey);
        cValues.put(RATINGS,rating);
        cValues.put(SIZE,size);
        //Insert the new row, returning the primary key value of the new row
        long l=db.insert(TABEL_NAME, null, cValues);
        Log.v("tag","dataInserted----->"+l);
        //db.close();
    }

}
