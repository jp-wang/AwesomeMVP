package io.jp.awesomemvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;

public class UrlMainActivity extends Activity {

    EditText editText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.url_main);
        editText = findViewById(R.id.url_et);
        findViewById(R.id.go).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                String url = editText.getText().toString();
                if (!url.isEmpty()) {
                    Intent intent = new Intent(UrlMainActivity.this, UrlBrowserActivity.class);
                    intent.putExtra(UrlBrowserActivity.KEY_URL, url);
                    startActivity(intent);
                }
            }
        });
    }
}
