package com.marca.mobileproject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class Utils {
    static final String HOMEFRAGMENT = "HomeFragment";
    static final String EVENTFRAGMENT = "EventFragment";
    static final String GROUPFRAGMENT = "GroupFragment";
    static final String LITURGYFRAGMENT = "LiturgyFragment";
    static final String HOURSFRAGMENT = "HoursFragment";
    static final String NEWSFRAGMENT = "NewsFragment";

    static void replaceFragment(Fragment fragment, String tag, FragmentActivity activity){
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, fragment, tag);

        //to go back to the HomeFragment when back is clicked
        if (fragment instanceof HomeFragment) {
            transaction.addToBackStack(null);
        } else {
            transaction.addToBackStack(HOMEFRAGMENT);
        }

        // Commit the transaction
        transaction.commit();
    }

}
