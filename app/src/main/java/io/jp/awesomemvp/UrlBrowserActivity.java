package io.jp.awesomemvp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class UrlBrowserActivity extends Activity {
    public static final String KEY_URL = "URL";
    private WebView webView;

    private TextView webRawTv;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();//Use it to manage Rx lifecycle to avoid memory leak

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.url_browser);
        webView = findViewById(R.id.web_browser);
        webView.setWebViewClient(new WebViewClient());

        webRawTv = findViewById(R.id.web_raw_content);

        String url;
        if ((url = getIntent().getStringExtra(KEY_URL)) != null) {
            webView.loadUrl(url);
            compositeDisposable.add(Observable.just(url)
                    .flatMap(s -> getRawContent(s))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(content -> webRawTv.setText(content.string()),
                    error -> Toast.makeText(this, "Cannot load the raw content", Toast.LENGTH_SHORT).show()));
        }
    }

    private Observable<ResponseBody> getRawContent(String url) {
        return WebContentServiceFactory.singleInstance().getRawContent(url);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
