package com.marca.mobileproject.about;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new TimetableFragment();
            case 1:
                return new SecretaryFragment();
            case 2:
                return new ContactFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}