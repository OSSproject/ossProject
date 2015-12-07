package embedded.kookmin.ac.kr.projectopensource;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import embedded.kookmin.ac.kr.database.DbOpenHelper;

public class MakeActivity extends AppCompatActivity implements View.OnClickListener{

    // Database 관련 객체들
    public DbOpenHelper db;
    SQLiteDatabase database;

    // layout object
    EditText needPerson;
    EditText numPerson;
    EditText comment;
    Button bInsert;
    Button bBack;

    ArrayAdapter<String> DbAdapter;
    ArrayList<String> teamList;

    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
        Intent intent = this.getIntent();
        tableName = intent.getStringExtra("contestTable");
        db = new DbOpenHelper(this);
        database = db.getReadableDatabase();


        needPerson = (EditText) findViewById(R.id.whos);
        numPerson = (EditText) findViewById(R.id.numbers);
        comment = (EditText) findViewById(R.id.editText_content);

        bInsert = (Button) findViewById(R.id.button_ok);
        bInsert.setOnClickListener(this);

        bBack = (Button) findViewById(R.id.button_cancel);
        bBack.setOnClickListener(this);

        // Create listview
        teamList = new ArrayList<String>();
    }

    //Button 클릭 이벤트
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok:
                String need = needPerson.getText().toString();
                int num = Integer.parseInt(numPerson.getText().toString());
                String comm = comment.getText().toString();

                db.insertContestInfo(tableName, need, num, comm);

                Toast.makeText(getApplicationContext(), "팀 등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                finish();
                break;
            case R.id.button_cancel:
                finish();
                break;
        }
    }
}
