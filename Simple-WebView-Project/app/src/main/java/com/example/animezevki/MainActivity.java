package com.example.animezevki;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bagla();
        yukle();

        // WebViewClient'i ayarla
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Sayfa yüklendiğinde ProgressBar'u gizle
                progressBar.setVisibility(android.view.View.GONE);
                // SwipeRefreshLayout'u durdur (eğer yükleniyorsa)
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // WebChromeClient'i ayarla
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // Sayfa yüklenirken ProgressBar'u güncelle
                progressBar.setProgress(newProgress);

                // Sayfa yüklenince ProgressBar'u gizle
                if (newProgress == 100) {
                    progressBar.setVisibility(android.view.View.GONE);
                } else {
                    progressBar.setVisibility(android.view.View.VISIBLE);
                }
            }
        });

        // SwipeRefreshLayout'u yenileme olayını dinle
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // SwipeRefreshLayout'u yenile
                webView.reload();
            }
        });

        // URL'yi al
        String url = getIntent().getStringExtra("url");

        if (url != null && !url.isEmpty()) {
            webView.loadUrl(url);
        }
    }

    public void bagla() {
        webView = findViewById(R.id.web);
        progressBar = findViewById(R.id.progressBar);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
    }

    public void yukle() {
        webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        // WebView geri gidebiliyorsa, geri git
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            // WebView geri gidemiyorsa, varsayılan davranışı gerçekleştir
            super.onBackPressed();
        }
    }
}
