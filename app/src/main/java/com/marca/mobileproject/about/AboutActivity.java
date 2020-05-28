package com.marca.mobileproject.about;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.marca.mobileproject.R;
import com.marca.mobileproject.Utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ViewPager2 viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new AboutViewPagerAdapter(this));
        Utils.setUpToolbar(this, "");

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setIcon(R.drawable.ic_timetables_24dp);
                    break;
                case 1:
                    tab.setIcon(R.drawable.ic_secretary_24dp);
                    break;
                case 2:
                    tab.setIcon(R.drawable.ic_contact_dark_24dp);
                break;
            }
        }).attach();
    }
}
