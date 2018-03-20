package acm.event.codetocreate18.View.Fragments;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.webkit.WebView;

import acm.event.codetocreate18.R;

public class contactWeb extends AppCompatActivity {

    private ProgressBar progressBar;
    WebView webview;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_web);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        webview=(WebView) findViewById(R.id.web);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Code2Create'18");
        webview.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebChromeClient(new WebChromeClient(){
            public void onProgressChanged(WebView view, int progress)
            {
                progressBar.setProgress(progress);
                if(progress == 100)
                {
                    progressBar.setVisibility(View.GONE);
                }
                super.onProgressChanged(view, progress);
            }
        });
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view,url, favicon);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                webview.setVisibility(View.VISIBLE);
            }

        });
        webview.loadUrl(getIntent().getStringExtra("url"));
        progressBar.setProgress(0);
    }

}
