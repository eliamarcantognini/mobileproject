package com.marca.mobileproject.hours;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.marca.mobileproject.R;

public class HoursActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hours);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HoursFragment hoursFragment = new HoursFragment();
        fragmentTransaction.add(R.id.hours_layout, hoursFragment);
        fragmentTransaction.commit();
    }

    /**
     * Listener for bottom sheet modal buttons
     * @param view
     *      The event source
     */
    public void hoursClick(final View view) {

        final WebView webView = findViewById(R.id.hours_webview);
        final Button button = findViewById(R.id.bottom_sheet_btn);
        final BottomSheetFragment bottomSheetFragment = HoursFragment.getBottomSheetFragment();
        webView.setVisibility(View.INVISIBLE);

        switch (view.getId()) {
            case R.id.invitatory:
                button.setText(R.string.invitatory);
                webView.loadUrl("https://www.chiesacattolica.it/la-liturgia-delle-ore/?ora=invitatorio");
                break;
            case R.id.lecture:
                button.setText(R.string.lectures_office);
                webView.loadUrl("https://www.chiesacattolica.it/la-liturgia-delle-ore/?ora=ufficio-delle-letture");
                break;
            case R.id.laude:
                button.setText(R.string.laude);
                webView.loadUrl("https://www.chiesacattolica.it/la-liturgia-delle-ore/?ora=lodi-mattutine");
                break;
            case R.id.medium:
                button.setText(R.string.medium);
                webView.loadUrl("https://www.chiesacattolica.it/la-liturgia-delle-ore/?ora=ora-media");
                break;
            case R.id.vesper:
                button.setText(R.string.vesper);
                webView.loadUrl("https://www.chiesacattolica.it/la-liturgia-delle-ore/?ora=vespri");
                break;
            case R.id.compline:
                button.setText(R.string.compline);
                webView.loadUrl("https://www.chiesacattolica.it/la-liturgia-delle-ore/?ora=compieta");
                break;
        }
        bottomSheetFragment.dismiss();
    }
}
