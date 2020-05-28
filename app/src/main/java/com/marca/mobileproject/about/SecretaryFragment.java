package com.marca.mobileproject.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.marca.mobileproject.R;
import com.marca.mobileproject.Utils;

public class SecretaryFragment extends Fragment {

    private static final String SECRETARY_PATH = "secretary/";
    private final String TITLE = getString(R.string.secretary);
    private final DatabaseReference db = FirebaseDatabase.getInstance().getReference(SECRETARY_PATH);
    private TextView monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.secretary_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            monday = activity.findViewById(R.id.mondayTextViewSecretary);
            tuesday = activity.findViewById(R.id.tuesdayTextViewSecretary);
            wednesday = activity.findViewById(R.id.wednesdayTextViewSecretary);
            thursday = activity.findViewById(R.id.thursdayTextViewSecretary);
            friday = activity.findViewById(R.id.fridayTextViewSecretary);
            saturday = activity.findViewById(R.id.saturdayTextViewSecretary);
            sunday = activity.findViewById(R.id.sundayTextViewSecretary);
            initListeners();

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Utils.setToolbarTitle(requireActivity(), TITLE);
    }

    /**
     * Initialize value listeners.
     * If time on Firebase backend change, then value in TextView is updated.
     */
    private void initListeners() {
        // Monday
        db.child("monday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String s = dataSnapshot.getValue(String.class);
                monday.setText(s);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                monday.setText(R.string.time_placeholder);
            }
        });
        // Tuesday
        db.child("tuesday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String s = dataSnapshot.getValue(String.class);
                tuesday.setText(s);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                tuesday.setText(R.string.time_placeholder);
            }
        });
        // Wednesday
        db.child("wednesday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String s = dataSnapshot.getValue(String.class);
                wednesday.setText(s);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                wednesday.setText(R.string.time_placeholder);
            }
        });
        // Thursday
        db.child("thursday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String s = dataSnapshot.getValue(String.class);
                thursday.setText(s);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                thursday.setText(R.string.time_placeholder);
            }
        });
        // Friday
        db.child("friday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String s = dataSnapshot.getValue(String.class);
                friday.setText(s);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                friday.setText(R.string.time_placeholder);
            }
        });
        // Saturday
        db.child("saturday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String s = dataSnapshot.getValue(String.class);
                saturday.setText(s);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                saturday.setText(R.string.time_placeholder);
            }
        });
        // Sunday
        db.child("sunday").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String s = dataSnapshot.getValue(String.class);
                sunday.setText(s);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                sunday.setText(R.string.time_placeholder);
            }
        });
    }
}
