package embedded.kookmin.ac.kr.projectopensource;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import embedded.kookmin.ac.kr.database.DbOpenHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";
    public DbOpenHelper db; //공모전 info DB 저장
    public SQLiteDatabase database;

    TextView textView;
    Button bRecent;
    Button bCount;
    Intent intent;

    String recent = null;
    String count = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();

        textView.setText("I Want You~");

        db = new DbOpenHelper(this); //DB Open
        database = db.getWritableDatabase();
        insertData();
        showData();

        intent = new Intent(this, SlideActivity.class);

        recent = "SHOW TABLE_SHCEMA, COUNT(*) FROM ";
        count = "SHOW TABLE";


        bRecent.setOnClickListener(this);
        bCount.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_recent: //공모전 최신순
                intent.putExtra("type", recent);
                startActivity(intent);
                break;
            case R.id.bt_numList: //공모전 모집글순
                intent.putExtra("type", count);
                startActivity(intent);
        }
    }
    public void insertData() { //DB에 정보 넣어줌
        db.insertContestName("Seoul");
        db.insertContestName("Web");
        db.insertContestName("Kangwon");
        db.insertContestName("Design");
        db.insertContestName("Korea");

    }
    public void showData() { //Android Monitor상에서의 저장된 DB 보여줌
        String sql = "SELECT * FROM " + "contestList";
        try {
            database = db.getReadableDatabase();
            Cursor cursor = database.rawQuery(sql, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                Log.d("Team List ", "index= " + id + " name=" + name); //Log
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception e) {
            Log.d("contest List ", "error in setAddress : " + e.toString());
        }
    }
    public void findViewById() { //모든 해당 버튼들을 찾아줌
        textView = (TextView) findViewById(R.id.textView);
        bRecent = (Button) findViewById(R.id.bt_recent);
        bCount = (Button) findViewById(R.id.bt_numList);
    }
}
