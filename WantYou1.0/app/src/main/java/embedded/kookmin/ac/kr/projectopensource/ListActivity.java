package embedded.kookmin.ac.kr.projectopensource;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import embedded.kookmin.ac.kr.database.DbOpenHelper;

public class ListActivity extends AppCompatActivity {

    ListView teamListView;
    ArrayList<String> tList;
    ArrayAdapter<String> adapter;

    SQLiteDatabase database;
    DbOpenHelper db;

    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = new DbOpenHelper(this);
        database = db.getReadableDatabase();

        Intent from = this.getIntent();
        tableName = from.getStringExtra("contestTable");

        db.subOnCreate(database, tableName);

        final Intent list = new Intent(this, MakeActivity.class);
        list.putExtra("contestTable", tableName);

        Button bMake = (Button) findViewById(R.id.bt_make);
        bMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(list);
            }
        });

        Button bBackSlide = (Button) findViewById(R.id.bt_backToSlide);
        bBackSlide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tList = new ArrayList<String>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tList);

        String sql = "SELECT * FROM " + tableName;
        try { //해당 공모전 안에 있는 teamList 보여주기
            Cursor cursor = database.rawQuery(sql, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String needPerson = cursor.getString(1);
                Log.d("Team List ", "index= " + id + " needPerson=" + needPerson); //Log


                tList.add(needPerson);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            Log.d("Team List in kangwon", "error in setAddress : " + e.toString());
        }

        teamListView = (ListView) findViewById(R.id.teamList);
        teamListView.setAdapter(adapter);
    }
}

