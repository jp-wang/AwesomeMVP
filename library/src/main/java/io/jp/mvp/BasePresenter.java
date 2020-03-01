package io.jp.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

/**
 * @author jpwang
 * @since 8/16/16
 */
public abstract class BasePresenter<M, V extends IView> implements IPresenter<V, M> {
    private M model;
    private V view;

    @Override
    @UiThread
    public void attachView(@NonNull V view, @Nullable M model) {
        this.view = view;
        this.model = model;
    }

    @Override
    @UiThread
    public void detachView() {
        this.view = null;
    }

    @Override
    @UiThread
    public void onDestroy() {

    }

    @UiThread
    protected final void runViewAction(Action<V> action) {
        if (this.view != null) {
            action.call(this.view);
        }
    }

    @Nullable
    protected final M getModel() {
        return this.model;
    }

    protected interface Action<V extends IView> {
        void call(V view);
    }
}
