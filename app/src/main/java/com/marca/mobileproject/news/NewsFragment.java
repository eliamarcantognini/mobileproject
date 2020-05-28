package com.marca.mobileproject.news;

import android.app.Activity;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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
import com.marca.mobileproject.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class NewsFragment extends Fragment implements OnNewsListener{

    private static final String NEWS_PATH = "news/";
    private static final String TITLE = "News";
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
            Utils.setUpToolbar((AppCompatActivity) activity, TITLE);
            setHasOptionsMenu(true);
            db.orderByChild("date").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot dataSnapshot) {
                    final GenericTypeIndicator<List<News>> t = new GenericTypeIndicator<List<News>>() {};
                    news = dataSnapshot.getValue(t);
                    adapter.setData(news);
                }
                @Override
                public void onCancelled(@NotNull DatabaseError error) {
                    Log.d("ONCANCELLED", "Failed to read value.", error.toException());
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    private void initRecyclerView(final Activity activity) {
        recyclerView = activity.findViewById(R.id.news_recycler);
        recyclerView.setHasFixedSize(true);
        adapter = new NewsCardAdapter(this);
        recyclerView.setAdapter(adapter);
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

        TransitionManager.beginDelayedTransition(cardView, new Slide());
        expandableView.setVisibility(
                expandableView.getVisibility() == View.GONE ? View.VISIBLE : View.GONE );
    }
}
