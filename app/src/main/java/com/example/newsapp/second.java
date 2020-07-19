package com.example.newsapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class second extends AppCompatActivity {

    ProgressBar progressBar;
    WebView browser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        browser = findViewById(R.id.webView);
        browser.setWebViewClient(new MyBrowser());
        browser.setWebChromeClient(new WebChromeClient() {
                                       @Override
                                       public void onProgressChanged(WebView view, int newProgress) {
                                           setTitle("Loading....");
                                       }
                                   }
        );

        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        browser.clearCache(true);
        browser.clearView();
        browser.reload();
        browser.loadUrl(getIntent().getStringExtra("url"));


    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String u, Bitmap favicon) {

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

        }

        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            Toast.makeText(view.getContext(), "HTTP error " + errorResponse.getStatusCode(), Toast.LENGTH_LONG).show();
        }

    }
    }
