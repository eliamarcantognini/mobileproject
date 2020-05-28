package com.marca.mobileproject.liturgy;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.marca.mobileproject.R;
import com.marca.mobileproject.Utils;

public class LiturgyFragment extends Fragment {

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.liturgy_fragment, container, false);
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Utils.setUpToolbar((AppCompatActivity) activity, getString(R.string.liturgy));
            webView = activity.findViewById(R.id.liturgy_webview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setVisibility(View.INVISIBLE);
            webView.setWebViewClient(new LiturgyWebClient());
            webView.addJavascriptInterface(new LiturgyJavascriptInterface(), "CallToAnAndroidFunction");
            webView.loadUrl("https://www.chiesacattolica.it/liturgia-del-giorno/");
        }
    }

    private class LiturgyJavascriptInterface {
        @JavascriptInterface
        public void setVisible() {
            requireActivity().runOnUiThread(() -> webView.setVisibility(View.VISIBLE));
        }
    }
}
