package com.marca.mobileproject.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.marca.mobileproject.R;
import com.marca.mobileproject.Utils;

public class ContactFragment extends Fragment implements com.google.android.gms.maps.OnMapReadyCallback{

    private static final String INFO_PATH = "info/";
    private final DatabaseReference db = FirebaseDatabase.getInstance().getReference(INFO_PATH);
    private TextView tel, cap, city, street, mobile;
    private ImageButton mobileBtn, telBtn, msgBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.contact_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();

        if (activity != null) {
            MapView mapView = activity.findViewById(R.id.mapView);
            tel = activity.findViewById(R.id.telephone);
            cap = activity.findViewById(R.id.cap);
            city = activity.findViewById(R.id.city);
            street = activity.findViewById(R.id.street);
            mobile = activity.findViewById(R.id.mobile);
            mobileBtn = activity.findViewById(R.id.mobile_button);
            telBtn = activity.findViewById(R.id.call_button);
            msgBtn = activity.findViewById(R.id.msg_button);

            initListeners();

            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
            mapView.onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Utils.setToolbarTitle(requireActivity(), getString(R.string.contact));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final LatLng pos = new LatLng(4.4147757E01, 1.223517E01);
        if (googleMap != null) {
            googleMap.addMarker(new MarkerOptions().position(pos));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(
                                                    CameraPosition.builder()
                                                            .target(pos)
                                                            .zoom(17)
                                                            .bearing(90)
                                                            .tilt(40)
                                                            .build()));
        }
    }

    private void initListeners() {
        // Firebase

        // CAP listener
        db.child("cap").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String s = dataSnapshot.getValue(String.class);
                cap.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                cap.setText(R.string.cap);
            }
        });
        // City listener
        db.child("city").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String s = dataSnapshot.getValue(String.class);
                city.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                city.setText(R.string.city);
            }
        });
        // Mobile listener
        db.child("mobile").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String s = dataSnapshot.getValue(String.class);
                mobile.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mobile.setText(R.string._3317);
            }
        });
        // Street listener
        db.child("street").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String s = dataSnapshot.getValue(String.class);
                street.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                street.setText(R.string.street);
            }
        });
        // Telephone listener
        db.child("tel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String s = dataSnapshot.getValue(String.class);
                tel.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                tel.setText(R.string._0721);
            }
        });

        // Button listeners
        telBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel.getText()));
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        mobileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile.getText()));
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        });
        msgBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + mobile.getText()))
                    .putExtra("sms_body", "Hi, my name is");
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
        });

    }
}
