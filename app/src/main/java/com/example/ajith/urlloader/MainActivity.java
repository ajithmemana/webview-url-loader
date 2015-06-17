package com.example.ajith.urlloader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editText);

    }

    public void loadUrl(View v) {
        String url = editText.getText().toString();

        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);

    }

    public void clear(View v) {
        editText.setText("");
    }

    public void clearCache(View v) {
//        webview.clearHistory();
//        webview.clearFormData();
//        webview.clearCache(true);
        CookieSyncManager.createInstance(this);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();

        Toast.makeText(this, "Cache cleared", Toast.LENGTH_SHORT).show();

    }
}
