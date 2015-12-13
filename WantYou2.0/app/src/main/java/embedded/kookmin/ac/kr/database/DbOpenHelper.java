package embedded.kookmin.ac.kr.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbOpenHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "addressbook.sqlite";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "contestList";

    public DbOpenHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //처음 DB를 생성할 떄
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "( ID INTEGER PRIMARY KEY AUTOINCREMENT , contestName text UNIQUE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertContestName(String name){
        SQLiteDatabase database = getWritableDatabase();
        String query = "INSERT INTO " + TABLE_NAME
                + "(contestName) VALUES('" + name + "');";
        try {
            database.execSQL(query);
        } catch (Exception ex) {
            Log.e("error", "Exception in Drop address table  SQL" + ex.toString());
        }
    }

    public void insertContestInfo(String tableName, String title, int num, String comm, int opt1, int opt2, int opt3, int opt4) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("title", title);
        values.put("numPerson", num);
        values.put("comments", comm);
        values.put("develop", opt1);
        values.put("design", opt2);
        values.put("plan", opt3);
        values.put("etc", opt4);

        database.insert(tableName, null, values);
    }

    public void subOnCreate(SQLiteDatabase db, String tableName) {
        //db.execSQL("DROP TABLE IF EXISTS " + tableName);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + " ( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title text, " +
                "numPerson text, " +
                "comments text, " +
                "develop INTEGER, " +
                "design INTEGER, " +
                "plan INTEGER, " +
                "etc INTEGER)");
        Log.d("ListActivity : ", "subOnCreate");
    }
}

