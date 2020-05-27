package com.marca.mobileproject.liturgy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.marca.mobileproject.R;
import com.marca.mobileproject.Utils;

public class LiturgyFragment extends Fragment {

    private static final String TITLE = "Liturgy";
    private static WebView webView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.liturgy_fragment, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Utils.setUpToolbar((AppCompatActivity) activity, TITLE);
            webView = activity.findViewById(R.id.webview);
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
            getActivity().runOnUiThread((Runnable) () -> webView.setVisibility(View.VISIBLE));
        }
    }
}
