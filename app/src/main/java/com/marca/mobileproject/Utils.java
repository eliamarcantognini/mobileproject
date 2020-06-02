package com.marca.mobileproject;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

/**
 * Static utilities functions class
 */
public class Utils {
    static final String HOMEFRAGMENT = "HomeFragment";
    static final String EVENTFRAGMENT = "EventFragment";
    static final String GROUPFRAGMENT = "GroupFragment";
    static final String LITURGYFRAGMENT = "LiturgyFragment";
    static final String NEWSFRAGMENT = "NewsFragment";

    /**
     * Replace fragment in the main activity fragment container.
     * @param fragment
     *      The fragment to be set.
     * @param tag
     *      The tag of the fragment.
     * @param activity
     *      The activity context.
     */
    static void replaceFragment(final Fragment fragment, final String tag, final FragmentActivity activity){
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, fragment, tag);

        if (fragment instanceof HomeFragment) {
            transaction.disallowAddToBackStack();
        } else {
            transaction.addToBackStack(HOMEFRAGMENT);
        }

        transaction.commit();
    }

    /**
     * Set up the toolbar.
     * @param activity
     *      The activity context.
     * @param title
     *      The title of the toolbar.
     */
    public static void setUpToolbar(final AppCompatActivity activity, final String title) {
        Toolbar toolbar = activity.findViewById(R.id.app_bar);
        toolbar.setTitle(title);
        activity.setSupportActionBar(toolbar);
            // Add back button to action bar for the about activity
        if (title.equals(activity.getString(R.string.contact)) ||
                title.equals(activity.getString(R.string.secretary)) ||
                title.equals(activity.getString(R.string.timetables))) {
            Objects.requireNonNull(activity.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        } else {
            // Back listener for fragments of main activity
        toolbar.setNavigationIcon(R.drawable.ic_back_24dp);
        toolbar.setNavigationOnClickListener(v -> activity.startActivity(new Intent(
                activity.getApplicationContext(), MainActivity.class)));
        }
    }

    /**
     * Set toolbar title.
     * @param activity
     *      The activity context.
     * @param title
     *      The title to be set.
     */
    public static void setToolbarTitle(final FragmentActivity activity, final String title) {
        Toolbar toolbar = activity.findViewById(R.id.app_bar);
        toolbar.setTitle(title);
    }
}
