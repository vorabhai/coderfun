package cn.hugeterry.coderfun.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;

import cn.hugeterry.coderfun.CoderfunCache;
import cn.hugeterry.coderfun.R;
import cn.hugeterry.coderfun.adapter.MyPagerAdapter;
import cn.hugeterry.coderfun.fragment.ReadFragment;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/2/23 21:53
 */
public class ReadActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager vp;
    private ArrayList<Fragment> mFragments;
    private final String[] mTitles = {"Android", "iOS", "前端", "拓展资源"};
    private int numToSetCurrentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CoderfunCache.isBackFromWebOrImage = true;
        numToSetCurrentItem = getIntent().getIntExtra("numToSetCurrentItem", 0);
        initToolbar();
        initFragments();
        initViewPager();
        initTabLayout();
        vp.setCurrentItem(numToSetCurrentItem);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_white_24dp);
        getSupportActionBar().setTitle("分类阅读");
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        for (String title : mTitles) {
            mFragments.add(ReadFragment.getInstance(title));
        }
    }

    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setOffscreenPageLimit(4);
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }

    private void initTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(vp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
