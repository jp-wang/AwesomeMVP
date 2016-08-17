package io.jp.mvp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author jpwang
 * @since 8/16/16
 */
public abstract class BaseFragment<M, V extends IView, P extends IPresenter<V, M>> extends Fragment {
    @SuppressWarnings("unchecked")
    private MVPDelegate<M, V, P> mvpDelegate = new MVPDelegate<M, V, P>((V)this, getSerializer()) {
        @NonNull
        @Override
        protected P createPresenter() {
            return BaseFragment.this.createPresenter();
        }

        @Nullable
        @Override
        protected M createModel() {
            return BaseFragment.this.createModel();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mvpDelegate.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mvpDelegate.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        mvpDelegate.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mvpDelegate.onStop();
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
}
