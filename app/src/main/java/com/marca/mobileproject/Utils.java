package com.marca.mobileproject;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class Utils {
    static final String HOMEFRAGMENT = "HomeFragment";
    static final String EVENTFRAGMENT = "EventFragment";
    static final String GROUPFRAGMENT = "GroupFragment";
    static final String LITURGYFRAGMENT = "LiturgyFragment";
    static final String HOURSFRAGMENT = "HoursFragment";
    static final String NEWSFRAGMENT = "NewsFragment";
    static final String CONTACT = "Contact";
    static final String SECRETARY = "Secretary";
    static final String TIMETABLE = "Timetable";

    public static void replaceFragment(final Fragment fragment, final String tag, final FragmentActivity activity){
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

    public static void setUpToolbar(final AppCompatActivity activity, final String title) {
        Toolbar toolbar = activity.findViewById(R.id.app_bar);
        toolbar.setTitle(title);
        activity.setSupportActionBar(toolbar);
        if (title.equals(CONTACT) || title.equals(SECRETARY) || title.equals(TIMETABLE)) {
            Objects.requireNonNull(activity.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        } else {
            /**
             * Back listener
             */
        toolbar.setNavigationIcon(R.drawable.ic_back_24dp);
        toolbar.setNavigationOnClickListener(v -> {
            activity.startActivity(new Intent(activity.getApplicationContext(), MainActivity.class));
        });
        }
    }

    public static void setToolbarTitle(final FragmentActivity activity, final String title) {
        Toolbar toolbar = activity.findViewById(R.id.app_bar);
        toolbar.setTitle(title);
    }

}
