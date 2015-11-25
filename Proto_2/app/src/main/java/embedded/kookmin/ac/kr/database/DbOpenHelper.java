package embedded.kookmin.ac.kr.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by 승수 on 2015-11-22.
 */
public class DbOpenHelper {

    private static final String DATABASE_NAME = "addressbook.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper (Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) { //처음 DB를 생성할 떄
            db.execSQL(ContestList.CreateDB.CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //데이터 베이스의 버전이 업그레이드 될 경우
//            db.execSQL("DROP TABLE IF EXIST" + ContestList.CreateDB.TABLENAME);
//            onCreate(db);
        }
    }

    public DbOpenHelper(Context context) {
        this.mCtx = context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDB.close();
    }

    // Insert DB
    public long insertColumn(String name){
        ContentValues values = new ContentValues();
        values.put(ContestList.CreateDB.NAME, name);

        return mDB.insert(ContestList.CreateDB.TABLENAME, null, values);
    }

    // Update DB
    public boolean updateColumn(long id , String name){
        ContentValues values = new ContentValues();
        values.put(ContestList.CreateDB.NAME, name);

        return mDB.update(ContestList.CreateDB.TABLENAME, values, "_id="+id, null) > 0;
    }

    // Delete ID
    public boolean deleteColumn(long id){
        return mDB.delete(ContestList.CreateDB.TABLENAME, "_id="+id, null) > 0;
    }

    // Delete Contact
    public boolean deleteColumn(String number){
        return mDB.delete(ContestList.CreateDB.TABLENAME, "contact="+number, null) > 0;
    }

    // Select All
    public Cursor getAllColumns(){
        return mDB.query(ContestList.CreateDB.TABLENAME, null, null, null, null, null, null);
    }

    // ID 컬럼 얻어 오기
    public Cursor getColumn(long id){
        Cursor c = mDB.query(ContestList.CreateDB.TABLENAME, null,
                "_id="+id, null, null, null, null);
        if(c != null && c.getCount() != 0)
            c.moveToFirst();
        return c;
    }

    // 이름 검색 하기 (rawQuery)
    public Cursor getMatchName(String name){
        Cursor c = mDB.rawQuery( "select * from address where name=" + "'" + name + "'" , null);
        return c;
    }
}
