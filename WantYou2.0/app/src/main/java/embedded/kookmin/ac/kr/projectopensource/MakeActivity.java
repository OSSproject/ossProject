package embedded.kookmin.ac.kr.projectopensource;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import embedded.kookmin.ac.kr.database.DbOpenHelper;

public class MakeActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{

    // Database object
    public DbOpenHelper db;
    SQLiteDatabase database;

    // layout object
    EditText title;
    EditText numPerson;
    EditText comment;
    Button bInsert;
    Button bBack;
    CheckBox option1; //개발자
    CheckBox option2; //디자이너
    CheckBox option3; //기획자
    CheckBox option4; //기타

    int opt1 = 0;
    int opt2 = 0;
    int opt3 = 0;
    int opt4 = 0;

    ArrayAdapter<String> DbAdapter;
    ArrayList<String> teamList;

    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);
        findviewById();

        Intent intent = this.getIntent();//from ListActivity
        tableName = intent.getStringExtra("contestTable");

        db = new DbOpenHelper(this);
        database = db.getReadableDatabase();

        bInsert.setOnClickListener(this);
        bBack.setOnClickListener(this);

        option1.setOnCheckedChangeListener(this);
        option2.setOnCheckedChangeListener(this);
        option3.setOnCheckedChangeListener(this);
        option4.setOnCheckedChangeListener(this);

        // Create listview
        teamList = new ArrayList<String>();
    }
    //Button 클릭 이벤트
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok:
                String topic = title.getText().toString();
                int num = Integer.parseInt(numPerson.getText().toString());
                String comm = comment.getText().toString();

                db.insertContestInfo(tableName, topic, num, comm, opt1, opt2, opt3, opt4);

                Toast.makeText(getApplicationContext(), "팀 등록이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                finish();
                break;
            case R.id.button_cancel:
                finish();
                break;
        }
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.developCheck) {
            if(isChecked) {
                opt1 = 1;
            }
        }
        if(buttonView.getId() == R.id.designCheck) {
            if(isChecked) {
                opt2 = 1;
            }
        }
        if(buttonView.getId() == R.id.planCheck) {
            if(isChecked) {
                opt3 = 1;
            }
        }
        if(buttonView.getId() == R.id.etcCheck) {
            if(isChecked) {
                opt4 = 1;
            }
        }
    }
    public void findviewById() {
        title = (EditText) findViewById(R.id.title);
        numPerson = (EditText) findViewById(R.id.numbers);
        comment = (EditText) findViewById(R.id.editText_content);
        bInsert = (Button) findViewById(R.id.button_ok);
        bBack = (Button) findViewById(R.id.button_cancel);
        option1 = (CheckBox) findViewById(R.id.developCheck);
        option2 = (CheckBox) findViewById(R.id.designCheck);
        option3 = (CheckBox) findViewById(R.id.planCheck);
        option4 = (CheckBox) findViewById(R.id.etcCheck);
    }
}
