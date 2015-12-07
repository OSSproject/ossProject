package embedded.kookmin.ac.kr.projectopensource;

    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.support.v4.app.FragmentPagerAdapter;
    import android.support.v4.view.ViewPager;
    import android.support.v7.app.AppCompatActivity;
    import android.util.Log;

    import embedded.kookmin.ac.kr.database.DbOpenHelper;

    public class SlideActivity extends AppCompatActivity {

        int MAX_PAGE = 5; //최대 페이지 갯수
        Fragment cur_fragment = new Fragment();

        private static final String TAG = "SlideActivity";
    public DbOpenHelper mDbOpenHelper; //해당 공모전 정보를 넣는  객체
    public SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        mDbOpenHelper = new DbOpenHelper(this); //DB Open
        insertData();//이미 한번 data가 들어가있음. 안들어갔다면 주석풀고 한번 넣어주어야 함.
        showData();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager())); //뷰페이저에 새로운 프레그먼트 삽입

    }


    public void insertData() { //DB에 들어갈 정보
        mDbOpenHelper.insertContestName("Seoul");
        mDbOpenHelper.insertContestName("Web");
        mDbOpenHelper.insertContestName("Kangwon");
        mDbOpenHelper.insertContestName("Design");
        mDbOpenHelper.insertContestName("Korea");

    }


protected class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) { //해당 슬라이드 페이지를 받아온다
        if (position < 0 || MAX_PAGE <= position)
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
            case 3:
                cur_fragment = new page_4();
                break;
            case 4:
                cur_fragment = new page_5();
                break;
        }
        return cur_fragment;
    }

    public int getCount() {
        return MAX_PAGE;
    }

}

    public void showData() {
        String sql = "SELECT * FROM " + "contestList";
        try {
            database = mDbOpenHelper.getReadableDatabase();
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
}