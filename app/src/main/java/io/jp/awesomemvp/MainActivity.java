package io.jp.awesomemvp;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import io.jp.mvp.BaseActivity;

public class MainActivity extends BaseActivity<Void, MainContract.IMainView, MainContract.IMainPresenter> implements MainContract.IMainView {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        editText = (EditText) this.findViewById(R.id.edit);
    }

    @Override
    protected MainContract.IMainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected Void createModel() {
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void doCalculate(View v) {
        String input = editText.getText().toString();
        if (input.isEmpty()) {
            Toast.makeText(this, "Please input your terms!", Toast.LENGTH_SHORT).show();
            return;
        }
        getPresenter().calculatePI(Long.valueOf(input));
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
