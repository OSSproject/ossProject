package embedded.kookmin.ac.kr.database;

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
        db.execSQL("create table " + TABLE_NAME + "( id integer primary key autoincrement null, name text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertAddress(String name){
        SQLiteDatabase database = getWritableDatabase();
        String query = "insert into " + TABLE_NAME
                + "(name) values('" + name + "');";
        try {
            database.execSQL(query);
        } catch (Exception ex) {
            Log.e("error", "Exception in Drop address table  SQL" + ex.toString());
        }
    }
    public void insertContestTeamList(String team, String contest) {
        SQLiteDatabase database = getWritableDatabase();
        String query = "insert into " + contest
                + "(team) values('" + team + "');";
        try {
            database.execSQL(query);
        } catch (Exception ex) {
            Log.e("error", "Exception in :" + ex.toString());
        }
    }
    public void CreateTable(String tableName, SQLiteDatabase db) {
        try {
            String sql = "create table " + tableName + "(id integer primary key autoincrement, " + "name text not null)";
            db.execSQL(sql);
        } catch (android.database.sqlite.SQLiteException e) {
            Log.d("Lab sqlite", "error: " + e);
        }
    }


}

