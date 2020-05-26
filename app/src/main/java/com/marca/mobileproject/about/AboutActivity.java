package com.marca.mobileproject.about;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.marca.mobileproject.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class AboutActivity extends AppCompatActivity {

    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new ViewPagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText(R.string.timetables);
                        tab.setIcon(R.drawable.ic_timetables_24dp);
                        break;
                    case 1:
                        tab.setText(R.string.secretary);
                        tab.setIcon(R.drawable.ic_secretary_24dp);
                        break;
                    case 2:
                        tab.setText(R.string.contact);
                        tab.setIcon(R.drawable.ic_contact_24dp);
                    break;
                }
            }
        }).attach();
    }


    private ViewPagerAdapter createAdapter() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        return adapter;
    }
}
