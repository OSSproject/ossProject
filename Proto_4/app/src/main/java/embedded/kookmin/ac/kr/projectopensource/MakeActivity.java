package embedded.kookmin.ac.kr.projectopensource;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MakeActivity extends AppCompatActivity implements View.OnClickListener{

    // Database 관련 객체들
    SQLiteDatabase db;
    String dbName = "teamList.db"; // name of Database;
    String tableName = "teamListTable"; // name of Table;
    int dbMode = Context.MODE_PRIVATE;
    String dbNeed = null;
    String dbComm = null;
    int dbNum = 0;

    // layout object
    EditText needPerson;
    EditText numPerson;
    EditText comment;
    Button bInsert;
    Button bBack;

    ArrayAdapter<String> DbAdapter;
    ArrayList<String> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);

        db = openOrCreateDatabase(dbName,dbMode,null);
        CreateTable();

        needPerson = (EditText) findViewById(R.id.whos);
        numPerson = (EditText) findViewById(R.id.numbers);
        comment = (EditText) findViewById(R.id.editText_content);

        bInsert = (Button) findViewById(R.id.button_ok);
        bInsert.setOnClickListener(this);

        bBack = (Button) findViewById(R.id.button_cancel);
        bBack.setOnClickListener(this);

        // Create listview
        teamList = new ArrayList<String>();
        DbAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, teamList);
    }

    //Button 클릭 이벤트
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok:
                String need = needPerson.getText().toString();
                int num = Integer.parseInt(numPerson.getText().toString());
                String comm = comment.getText().toString();

                String query = String.format("INSERT INTO " + tableName + " VALUES(NULL, '%s', '%d', '%s');", need, num, comm);
                insertQuery(query);
                Toast.makeText(getApplicationContext(), "팀 등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_cancel:
                finish();
                break;
        }
    }

    // Table 생성
    public void CreateTable() {
        try {
            String sql = "create table " + tableName + "(id integer primary key autoincrement, " + "name text not null)";
            db.execSQL(sql);
        } catch (android.database.sqlite.SQLiteException e) {
            Log.d("Lab sqlite", "error: " + e);
        }
    }

    // Data 추가
    public void insertData(String name) {
        String sql = "insert into " + tableName + " values(NULL, '" + name + "');";
        db.execSQL(sql);
    }

    // Query parsing
    public void insertQuery(String query) {
        db.execSQL(query);
    }

    // 모든 Data 읽기
    public void selectAll() {
        String sql = "select * from " + tableName + ";";
        Cursor results = db.rawQuery(sql, null);
        results.moveToFirst();

        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String name = results.getString(1);
            Log.d("lab_sqlite", "index= " + id + " name=" + name);

            teamList.add(name);
            results.moveToNext();
        }
        results.close();
    }

}
