package com.tatuas.webview.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewDialogActivity extends AppCompatActivity {

    private static final String EXTRA_URL = "url";
    private static final String EXTRA_JAVASCRIPT = "javascript";

    private WebView webView;

    @NonNull
    public static Intent createIntent(@NonNull Context context,
                                      @NonNull String url,
                                      boolean enableJavaScript) {
        final Intent i = new Intent(context, WebViewDialogActivity.class);
        i.putExtra(EXTRA_URL, url);
        i.putExtra(EXTRA_JAVASCRIPT, enableJavaScript);
        return i;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_dialog);

        webView = (WebView) findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        final WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(getIntent().getBooleanExtra(EXTRA_JAVASCRIPT, false));

        webView.loadUrl(getIntent().getStringExtra(EXTRA_URL));
    }

    @Override
    public void finish() {
        Toast.makeText(WebViewDialogActivity.this, "Disable Finish", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.finish();
        }
    }
}
