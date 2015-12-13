package embedded.kookmin.ac.kr.projectopensource;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class SlideActivity extends AppCompatActivity {

    int MAX_PAGE = 5; //최대 페이지 갯수
    Fragment cur_fragment = new Fragment();
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);

        Intent intent =  this.getIntent(); //from MainActivity
        type = intent.getStringExtra("type");// type = recent or count

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager())); //뷰페이저에 새로운 프레그먼트 삽입

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
}