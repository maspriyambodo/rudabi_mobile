package com.bimasislam.rudabi;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.Objects;
import android.app.ProgressDialog;
import android.webkit.WebViewClient;


public class Dashboard extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Objects.requireNonNull(getSupportActionBar()).hide();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMax(100);
        progressDialog.setProgress(0);
        progressDialog.show();

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        SwipeRefreshLayout mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swipeContainer);
        myWebView.requestFocus();
        WebSettings webSettings = myWebView.getSettings();
        myWebView.getSettings().setGeolocationEnabled(true);
        webSettings.setJavaScriptEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.loadUrl("https://rudabi.kemenag.dev/");
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        myWebView.reload();
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

        myWebView.setWebViewClient(new WebViewClient(){

        });
    }

}