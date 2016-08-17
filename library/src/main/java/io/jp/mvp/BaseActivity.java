package io.jp.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author jpwang
 * @since 8/16/16
 */
public abstract class BaseActivity<M, V extends IView, P extends IPresenter<V, M>> extends Activity implements IView {
    @SuppressWarnings("unchecked")
    private MVPDelegate<M, V, P> mvpDelegate = new MVPDelegate<M, V, P>((V)this, getSerializer()) {
        @NonNull
        @Override
        protected P createPresenter() {
            return BaseActivity.this.createPresenter();
        }

        @Nullable
        @Override
        protected M createModel() {
            return BaseActivity.this.createModel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpDelegate.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mvpDelegate.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mvpDelegate.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mvpDelegate.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mvpDelegate.onSaveInstanceState(outState);
    }

    protected abstract P createPresenter();

    protected abstract M createModel();

    protected IModelSerializer<M> createSerializer() {
        return null;
    }

    private IModelSerializer<M> getSerializer() {
        IModelSerializer<M> serializer = createSerializer();
        if (serializer == null) {
            serializer = new DefaultModelSerializer<>();
        }
        return serializer;
    }

    protected final P getPresenter() {
        return mvpDelegate.getPresenter();
    }
}
