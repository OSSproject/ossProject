package embedded.kookmin.ac.kr.projectopensource;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import embedded.kookmin.ac.kr.database.DbOpenHelper;

public class page_5 extends android.support.v4.app.Fragment { //슬라이드 마지막 페이지

    SQLiteDatabase database;
    DbOpenHelper dbOpenHelper;

    TextView textViewName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbOpenHelper = new DbOpenHelper(getActivity());
        database = dbOpenHelper.getReadableDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {

        LinearLayout linearLayout = (LinearLayout)
                inflater.inflate(R.layout.page, container, false);
//        LinearLayout linearLayout = (LinearLayout) //testCode
//                inflater.inflate(R.layout.page1, container, false);

        RelativeLayout background = (RelativeLayout)
                linearLayout.findViewById(R.id.background);
        background.setBackground(new ColorDrawable
                (Color.parseColor("#B0E0E6")));

        TextView page_num = (TextView) linearLayout.findViewById
                (R.id.page_num);
//        TextView page_num = (TextView) linearLayout.findViewById //testCode
//                (R.id.textView2);
        page_num.setText(String.valueOf(5));

        Button bBack = (Button) linearLayout.findViewById(R.id.bt_backMain);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        textViewName = (TextView) linearLayout.findViewById(R.id.DbData);
//        textViewName = (TextView) linearLayout.findViewById(R.id.textView3); //testCode

        Cursor cursor;

        String sql = "SELECT * from address";

        try {
            cursor = database.rawQuery(sql, null);
            if (cursor.moveToPosition(4)) {
                String name = cursor.getString(1);
                textViewName.append("\n" + name);
            }
        } catch (Exception e) {
            Log.d("page1 Fragment", "error in setAddress : " + e.toString());
        }

        return linearLayout;
    }
}
