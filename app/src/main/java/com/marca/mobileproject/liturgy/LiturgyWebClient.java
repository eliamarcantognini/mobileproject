package com.marca.mobileproject.liturgy;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LiturgyWebClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
    @Override
    public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);
        webView.loadUrl("javascript:(function() { " +
                "document.getElementsByTagName('header')[0].style.display='none'; " +
                "})()");
        webView.loadUrl("javascript:(function() { " +
                "document.getElementById('cci-skin-left').style.display='none';})()");
        webView.loadUrl("javascript:(function() { " +
                "document.getElementById('cci-skin-right').style.display='none';})()");
        webView.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('row cci-liturgia-menu')[0].style.display='none';})()");
        webView.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('cci_breadcrumb')[0].style.display='none';})()");
        webView.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('sow-image-container')[0].style.display='none';})()");
        webView.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('sow-image-container')[1].style.display='none';})()");
        webView.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('sow-image-container')[2].style.display='none';})()");
        webView.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('sow-image-container')[3].style.display='none';})()");
        webView.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('cci_content_single_share')[0].style.display='none';})()");
        webView.loadUrl("javascript:(function() { " +
                "document.getElementById('BEWEB-searchChronology').style.display='none';})()");
        webView.loadUrl("javascript:(function() { " +
                "document.getElementsByClassName('row cci_footer')[0].style.display='none';})()");
        webView.loadUrl("javascript:(function() { " +
                "window.CallToAnAndroidFunction.setVisible();})()");

    }

}
