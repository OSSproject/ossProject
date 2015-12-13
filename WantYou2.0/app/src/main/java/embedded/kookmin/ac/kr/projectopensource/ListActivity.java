package embedded.kookmin.ac.kr.projectopensource;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import embedded.kookmin.ac.kr.database.DbOpenHelper;

public class ListActivity extends AppCompatActivity implements View.OnClickListener{

    ListView teamListView;
    ArrayList<String> tList;
    ArrayAdapter<String> adapter;

    SQLiteDatabase database;
    DbOpenHelper db;

    String tableName, develop = "", design = "", plan = "", etc = "";
    Button bMake;
    Button bBackSlide;
    Intent list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        findViewById();

        db = new DbOpenHelper(this);
        database = db.getReadableDatabase();

        Intent from = this.getIntent(); //from page class
        tableName = from.getStringExtra("contestTable");
        db.subOnCreate(database, tableName); //공모전에 해당하는 subTable 생성

        list = new Intent(this, MakeActivity.class);
        list.putExtra("contestTable", tableName);

        bMake.setOnClickListener(this);
        bBackSlide.setOnClickListener(this);


        tList = new ArrayList<String>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tList);
//        ArrayList teamList = new ArrayList<InfoClass>();
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, teamList);

        String sql = "SELECT * FROM " + tableName;
        try { //해당 공모전 안에 있는 teamList 보여주기
            Cursor cursor = database.rawQuery(sql, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                int numPerson = cursor.getInt(2);
                String comment = cursor.getString(3);
                int develop = cursor.getInt(4);
                int design = cursor.getInt(5);
                int plan = cursor.getInt(6);
                int etc = cursor.getInt(7);
                Log.d("Team List ", "index= " + id + " title= " + title + " numPerson= " + numPerson + " comments= " + comment +
                "develop = " + develop + "design = " + design + "plan" + plan + "etc = " + etc); //Log

//                InfoClass team = new InfoClass(numPerson, needPerson, comment);
//                teamList.add(team);
                tList.add(title);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            Log.d("Team List in kangwon", "error in setAddress : " + e.toString());
        }

        teamListView.setAdapter(adapter);
        teamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {//ListView의 item Click이벤트
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog alertDialog = new AlertDialog.Builder(ListActivity.this).create();

                //database
                String sql = "SELECT * FROM " + tableName;
                Cursor cursor = database.rawQuery(sql, null);
                cursor.moveToPosition(position); //ListView의 선택 위치로 커서 변경

                String title = cursor.getString(1);
                int numPerson = cursor.getInt(2);
                String comment = cursor.getString(3);
                if(cursor.getInt(4) == 1)
                    develop = "개발자";
                if(cursor.getInt(5) == 1)
                    design = "디자이너";
                if(cursor.getInt(6) == 1)
                    plan = "기획자";
                if(cursor.getInt(7) == 1)
                    etc = "기타";

                alertDialog.setTitle("Title : " + title);

                alertDialog.setMessage(
                        "원하는 유형의 사람 : " + develop + " " + design + " " + plan + " " + etc + "\n\n" +
                        "필요로 하는 인원수 : " + numPerson + "\n\n"
                        + "Comments : \n" + comment
                );
                alertDialog.show();
            }
        });
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bt_make:
                startActivity(list);
                break;
            case R.id.bt_backToSlide:
                finish();
                break;
        }
    }
    public void findViewById() {
        bMake = (Button) findViewById(R.id.bt_make);
        bBackSlide = (Button) findViewById(R.id.bt_backToSlide);
        teamListView = (ListView) findViewById(R.id.teamList);
    }
}

