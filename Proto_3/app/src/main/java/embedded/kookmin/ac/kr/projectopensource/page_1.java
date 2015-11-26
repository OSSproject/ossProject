package embedded.kookmin.ac.kr.projectopensource;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class page_1 extends android.support.v4.app.Fragment { //슬라이드 첫 페이지
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup
            container, Bundle savedInstanceState) {

        final LinearLayout linearLayout = (LinearLayout)
                inflater.inflate(R.layout.page, container, false);

        RelativeLayout background = (RelativeLayout)
                linearLayout.findViewById(R.id.background);
        TextView page_num = (TextView) linearLayout.findViewById
                (R.id.page_num);
        page_num.setText(String.valueOf(1));
        Button bBack = (Button) linearLayout.findViewById(R.id.bt_back);
        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        background.setBackground(new ColorDrawable
                (Color.parseColor("#008B8B")));

        return linearLayout;
    }
}