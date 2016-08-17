package io.jp.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author jpwang
 * @since 8/16/16
 */
public abstract class BasePresenter<M, V extends IView> implements IPresenter<V, M> {
    private M model;
    private V view;

    @Override
    public void attachView(@NonNull V view, @Nullable M model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void onDestroy() {

    }

    @NonNull
    protected V getView() {
        return this.view;
    }

    @Nullable
    protected M getModel() {
        return this.model;
    }
}
