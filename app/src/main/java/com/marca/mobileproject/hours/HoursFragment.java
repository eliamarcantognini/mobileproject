package com.marca.mobileproject.hours;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.marca.mobileproject.R;
import com.marca.mobileproject.Utils;

public class HoursFragment extends Fragment{

    private static BottomSheetFragment bottomSheetFragment;
    private ProgressBar progressBar;
    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.hours_fragment, container, false);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Utils.setUpToolbar((AppCompatActivity) activity, getString(R.string.hours));
            progressBar = activity.findViewById(R.id.progressBar);
            progressBar.setVisibility(View.VISIBLE);
            activity.findViewById(R.id.bottom_sheet_btn).setOnClickListener(v -> {
                bottomSheetFragment = new BottomSheetFragment();
                bottomSheetFragment.show(activity.getSupportFragmentManager(), "sheet");
            });
            webView = activity.findViewById(R.id.hours_webview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setVisibility(View.INVISIBLE);
            webView.setWebViewClient(new HoursWebClient());
            webView.addJavascriptInterface(new HoursJavascriptInterface(), "CallToAnAndroidFunction");
            webView.loadUrl("https://www.chiesacattolica.it/la-liturgia-delle-ore/?ora=lodi-mattutine");
        }
    }

    private class HoursJavascriptInterface {
        @JavascriptInterface
        public void setVisible() {
            requireActivity().runOnUiThread(() -> {
                webView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            });

        }
    }

    static BottomSheetFragment getBottomSheetFragment() {
        return bottomSheetFragment;
    }
}
