package com.illumnus.illumnusEdu.illumnus;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;



import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private WebView wb;
    private AlertDialog alertDialog;

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
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                //TODO your background code
//                    if (!isNetworkConnected() ) {
//                        showAlertMsg();
//                    }
//            }
//        });
        startSyncThread();
        connectivity();

    }


    public void startSyncThread() {
        final Handler handler = new Handler();
        final int delay = 1000;

        handler.postDelayed(new Runnable() {
            public void run() {
                if (!isNetworkConnected() ) {
                    if(!alertDialog.isShowing())
                        showAlertMsg();
                    }
                handler.postDelayed(this, delay);
            }
        }, delay);
    }


    private void connectivity() {
        init();
    }


    private void showAlertMsg(){
        try {
            alertDialog.setTitle("Info");
            alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
            alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });

            alertDialog.show();
        } catch (Exception e) {
            Log.d("Error", "Show Dialog: " + e.getMessage());
        }
    }

    private void init() {
        alertDialog = new AlertDialog.Builder(this).create();
        wb = findViewById(R.id.webView1);
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


    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }


    // 2nd way to check using ping is internet is connected
    public boolean isConnected() throws InterruptedException, IOException {
        final String command = "ping -c 1 google.com";
        return Runtime.getRuntime().exec(command).waitFor() == 0;
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
