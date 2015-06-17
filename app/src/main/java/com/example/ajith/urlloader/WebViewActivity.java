package com.example.ajith.urlloader;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class WebViewActivity extends ActionBarActivity {
    WebView webview;
    double startTime;
    double stopTime;
    double loadingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewactivity);
        getSupportActionBar().setTitle("Loading...");
        String url = getIntent().getStringExtra("url");
     //   Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        final Activity activity = this;
        webview = (WebView) findViewById(R.id.wevbiew);


        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        final ProgressDialog progressBar = ProgressDialog.show(this, "Please wait", "Loading...");

        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                startTime = System.currentTimeMillis();

                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
                stopTime = System.currentTimeMillis();
                loadingTime = stopTime - startTime;
                stopTime = 0;
                startTime = 0;
                setPageTitle(loadingTime / 1000);
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Error: " + description, Toast.LENGTH_SHORT).show();
                alertDialog.setTitle("Error");
                alertDialog.setMessage(description);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
                alertDialog.show();
            }
        });
        webview.loadUrl(url);
    }


    private void setPageTitle(double time) {
        getSupportActionBar().setTitle("Duration: " + time + "s");
    }
}
