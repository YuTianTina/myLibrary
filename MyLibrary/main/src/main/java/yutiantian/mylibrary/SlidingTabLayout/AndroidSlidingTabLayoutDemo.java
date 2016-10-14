package yutiantian.mylibrary.SlidingTabLayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yutiantian.mylibrary.R;
import yutiantian.mylibrary.qrcode.QRCodeDemo2;

public class AndroidSlidingTabLayoutDemo extends AppCompatActivity {

    @Bind(R.id.sliding_tabs)
    SlidingTabLayout slidingTabs;
    @Bind(R.id.viewpage)
    ViewPager viewpage;
    List<View> viewList = new ArrayList<>();
    TabFra1 tabFra1;
    TabFra2 tabFra2;
    TabFra3 tabFra3;
    TabFra4 tabFra4;
    List<String> titles = new ArrayList<>();
    List<Fragment> fraList = new ArrayList<>();
    private static final String TAG="AndroidSlidingTabLayoutDemo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_sliding_tab_layout_demo);
        ButterKnife.bind(this);
        tabFra1 = new TabFra1();
        fraList.add(tabFra1);
        tabFra2 = new TabFra2();
        fraList.add(tabFra2);
        tabFra3 = new TabFra3();
        fraList.add(tabFra3);
        tabFra4 = new TabFra4();
        fraList.add(tabFra4);
        
        titles.add("tabFra1");
        titles.add("tabFra2");
        titles.add("tabFra3");
        titles.add("tabFra4");

        FraAdapter adapter = new FraAdapter(getSupportFragmentManager(), fraList, titles);
        viewpage.setAdapter(adapter);
        slidingTabs.setViewPager(viewpage);

    }

    @OnClick(R.id.btn_slidTab)
    public void onClick() {
        startActivity(new Intent(this, QRCodeDemo2.class));
    }

    private class SimpleAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Item" + position;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            container.removeView(viewList.get(position));
        }
    }

    private class FraAdapter extends FragmentPagerAdapter {

        private List<String> titles;
        private List<Fragment> mFragments;

        public FraAdapter(FragmentManager fm, List<Fragment> mFragments, List<String> titles) {
            super(fm);
            this.titles = titles;
            this.mFragments = mFragments;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }


        @Override
        public int getCount() {
            return mFragments.size();
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
