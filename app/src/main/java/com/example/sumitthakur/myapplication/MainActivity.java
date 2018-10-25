package com.example.sumitthakur.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView wb;

    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        wb=findViewById(R.id.webView1);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);
        wb.getSettings().setBuiltInZoomControls(true);
        wb.getSettings().setPluginState(WebSettings.PluginState.ON);
        wb.getSettings().setDomStorageEnabled(true);
        wb.canGoBack();
        //wb.getSettings().setPluginsEnabled(true);
        wb.setWebViewClient(new HelloWebViewClient());
        wb.loadUrl("https://app.illumnus.com");

    }


    @Override
    public void onBackPressed() {
        if (wb.canGoBack()) {
            wb.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
