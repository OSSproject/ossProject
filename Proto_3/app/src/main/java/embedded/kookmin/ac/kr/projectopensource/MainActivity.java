package embedded.kookmin.ac.kr.projectopensource;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";//db

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent(this, SlideActivity.class);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Choose which one you gonna want.");

        Button bRecent = (Button) findViewById(R.id.bt_recent);
        bRecent.setOnClickListener(new View.OnClickListener() { //버튼을 클릭하여 다음 엑티비티로 이동.
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}
