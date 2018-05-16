package com.vuthithom.fakecall.activity;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vuthithom.fakecall.fragment.FakeSmsFragment;
import com.vuthithom.fakecall.R;
import com.vuthithom.fakecall.fragment.ScheduleFragment;
import com.vuthithom.fakecall.ViewPagerAdapTer;
import com.vuthithom.fakecall.fakecall.FakeCallFragment;

public class MainActivity extends AppCompatActivity {
    ViewPagerAdapTer viewPagerAdapTer;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        final ViewPager viewPager = findViewById(R.id.vpager);
        viewPagerAdapTer=new ViewPagerAdapTer(getSupportFragmentManager());
        viewPagerAdapTer.AddFragment(new FakeCallFragment(),"FAKE CALL");
        viewPagerAdapTer.AddFragment(new FakeSmsFragment(),"FAKE SMS");
        viewPagerAdapTer.AddFragment(new ScheduleFragment(),"SCHEDULE");
        viewPager.setAdapter(viewPagerAdapTer);
        tabLayout.setupWithViewPager(viewPager);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setElevation(0);

    }

}

