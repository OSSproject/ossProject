package embedded.kookmin.ac.kr.projectopensource;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.sql.SQLException;
import java.util.ArrayList;

import embedded.kookmin.ac.kr.database.CustomAdapter;
import embedded.kookmin.ac.kr.database.DLog;
import embedded.kookmin.ac.kr.database.DbOpenHelper;
import embedded.kookmin.ac.kr.database.InfoClass;

public class SlideActivity extends AppCompatActivity {

    int MAX_PAGE = 3;
    Fragment cur_fragment = new Fragment();

    private static final String TAG = "SlideActivity";
    private DbOpenHelper mDbOpenHelper; //해당 공모전 정보를 넣는 객체
    private Cursor mCursor;
    private InfoClass mInfoClass;
    private ArrayList<InfoClass> mInfoArray;
    private CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        mDbOpenHelper = new DbOpenHelper(this);
        try {
            mDbOpenHelper.open();

            mDbOpenHelper.insertColumn("국민클라우드");
            mDbOpenHelper.insertColumn("세종시");
            mDbOpenHelper.insertColumn("노인복지");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mInfoArray = new ArrayList<InfoClass>();

        dowhileCursorToArray();


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager())); //뷰페이저에 새로운 프레그먼트 삽입

    }
    protected class FragmentAdapter extends FragmentPagerAdapter {
        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) { //해당 슬라이드 페이지를 받아온다
            if(position < 0 || MAX_PAGE <= position)
                return null;
            switch (position) {
                case 0:
                    cur_fragment = new page_1();
                    break;
                case 1:
                    cur_fragment = new page_2();
                    break;
                case 2:
                    cur_fragment = new page_3();
                    break;
            }
            return cur_fragment;
        }
        public int getCount() {
            return MAX_PAGE;
        }

    }
    private void dowhileCursorToArray() {

        mCursor = null;
        mCursor = mDbOpenHelper.getAllColumns();
        DLog.e(TAG, "COUNT = " + mCursor.getCount());

        while(mCursor.moveToNext()) {

            mInfoClass = new InfoClass(
                    mCursor.getInt(mCursor.getColumnIndex("_id")),
                    mCursor.getString(mCursor.getColumnIndex("name"))
            );
            mInfoArray.add(mInfoClass);
        }
        mCursor.close();
    }
}
