package com.marca.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.marca.mobileproject.about.AboutActivity;
import com.marca.mobileproject.event.EventFragment;
import com.marca.mobileproject.hours.HoursActivity;
import com.marca.mobileproject.news.NewsFragment;
import com.marca.mobileproject.liturgy.LiturgyFragment;

/**
 * Main menu fragment.
 */
public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentActivity activity = getActivity();
        if (activity != null) {
            // Event card click listener
            CardView eventCard = activity.findViewById(R.id.eventCard);
            eventCard.setOnClickListener(v -> {
                EventFragment eventFragment = new EventFragment();
                Utils.replaceFragment(eventFragment, Utils.EVENTFRAGMENT, activity);
            });
            // About card click listener
            CardView aboutCard = activity.findViewById(R.id.timetableCard);
            aboutCard.setOnClickListener(v -> {
                Intent aboutIntent = new Intent(activity.getApplicationContext(), AboutActivity.class);
                startActivity(aboutIntent);
            });
            // Group card click listener
            CardView groupCard = activity.findViewById(R.id.groupCard);
            groupCard.setOnClickListener(v -> {
                GroupFragment groupFragment = new GroupFragment();
                Utils.replaceFragment(groupFragment, Utils.GROUPFRAGMENT, activity);
            });
            // News card click listener
            CardView newsCard = activity.findViewById(R.id.newsCard);
            newsCard.setOnClickListener(v -> {
                NewsFragment newsFragment = new NewsFragment();
                Utils.replaceFragment(newsFragment, Utils.NEWSFRAGMENT, activity);
            });
            // Liturgy card click listener
            CardView liturgyCard = activity.findViewById(R.id.liturgyCard);
            liturgyCard.setOnClickListener(v -> {
                LiturgyFragment liturgyFragment = new LiturgyFragment();
                Utils.replaceFragment(liturgyFragment, Utils.LITURGYFRAGMENT, activity);
            });
            // Hours card click listener
            CardView hoursCard = activity.findViewById(R.id.hoursCard);
            hoursCard.setOnClickListener(v -> {
                Intent hoursIntent = new Intent(activity.getApplicationContext(), HoursActivity.class);
                startActivity(hoursIntent);
            });
        }
    }

}
