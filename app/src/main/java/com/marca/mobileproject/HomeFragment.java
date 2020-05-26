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
import com.marca.mobileproject.news.NewsFragment;
import com.marca.mobileproject.liturgy.HoursFragment;
import com.marca.mobileproject.liturgy.LiturgyFragment;

public class HomeFragment extends Fragment {


    private static final String LOG = "DEBUG: ";
    private CardView eventCard, timetableCard, groupCard, newsCard, liturgyCard, hoursCard;

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
            eventCard = (CardView) activity.findViewById(R.id.eventCard);
            eventCard.setOnClickListener(v -> {
                EventFragment eventFragment = new EventFragment();
                if (eventFragment != null) {
                    Utils.replaceFragment(eventFragment, Utils.EVENTFRAGMENT, activity);
                }
            });
            timetableCard = (CardView) activity.findViewById(R.id.timetableCard);
            timetableCard.setOnClickListener(v -> {
                Intent aboutIntent = new Intent(activity.getApplicationContext(), AboutActivity.class);
                startActivity(aboutIntent);
            });
            groupCard = (CardView) activity.findViewById(R.id.groupCard);
            groupCard.setOnClickListener(v -> {
                GroupFragment groupFragment = new GroupFragment();
                if (groupFragment != null) {
                    Utils.replaceFragment(groupFragment, Utils.GROUPFRAGMENT, activity);
                }
            });
            newsCard = (CardView) activity.findViewById(R.id.newsCard);
            newsCard.setOnClickListener(v -> {
                NewsFragment newsFragment = new NewsFragment();
                if (newsFragment != null) {
                    Utils.replaceFragment(newsFragment, Utils.NEWSFRAGMENT, activity);
                }
            });
            liturgyCard = (CardView) activity.findViewById(R.id.liturgyCard);
            liturgyCard.setOnClickListener(v -> {
                LiturgyFragment liturgyFragment = new LiturgyFragment();
                if (liturgyFragment != null) {
                    Utils.replaceFragment(liturgyFragment, Utils.LITURGYFRAGMENT, activity);
                }
            });
            hoursCard = (CardView) activity.findViewById(R.id.hoursCard);
            hoursCard.setOnClickListener(v -> {
                HoursFragment hoursFragment = new HoursFragment();
                if (hoursFragment != null) {
                    Utils.replaceFragment(hoursFragment, Utils.HOURSFRAGMENT, activity);
                }
            });
        }
    }

}
