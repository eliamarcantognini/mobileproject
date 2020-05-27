package com.marca.mobileproject.news;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.marca.mobileproject.R;

import java.util.List;
import java.util.Objects;

public class NewsFragment extends Fragment implements OnNewsListener{

    private static final String NEWS_PATH = "news/";
    private final DatabaseReference db = FirebaseDatabase.getInstance().getReference(NEWS_PATH);
    private RecyclerView recyclerView;
    private NewsCardAdapter adapter;
    private List<News> news;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.news_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();

        if (activity != null) {

            initRecyclerView(activity);
            db.orderByChild("date").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    final GenericTypeIndicator<List<News>> t = new GenericTypeIndicator<List<News>>() {};

                    news = dataSnapshot.getValue(t);
                    adapter.setData(news);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.d("ONCANCELLED", "Failed to read value.", error.toException());
                }
            });
        }
    }

    private void initRecyclerView(final Activity activity) {
        recyclerView = activity.findViewById(R.id.news_recycler);
        recyclerView.setHasFixedSize(true);
        adapter = new NewsCardAdapter(this);
        recyclerView.setAdapter(adapter);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
//        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Method called when the user click a card
     * @param position position of the item clicked
     */
    @Override
    public void onNewsClick(final int position) {
        final View view = Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(position)).itemView;
        final CardView cardView = view.findViewById(R.id.news_card);
        final View expandableView = cardView.findViewById(R.id.expandable_view);

        //transition to show the hidden layout of the card clicked
        TransitionManager.beginDelayedTransition(cardView, new Slide());
        expandableView.setVisibility(
                expandableView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE );
    }
}
