package io.jp.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author jpwang
 * @since 8/16/16
 *
 * The basic interface of presenter in MVP
 *
 * V - View
 * M - Model
 */
public interface IPresenter<V extends IView, M> {
    /**
     * Attach view and model after presenter was created
     *
     * @param view Will be attached into presenter
     * @param model Store the meta data of current MVP frame
     */
    void attachView(@NonNull V view, @Nullable M model);

    /**
     * Notify presenter that the view has been detached from window
     */
    void detachView();

    /**
     * Each presenter should recycle resources before it gets destroyed
     */
    void onDestroy();
}
