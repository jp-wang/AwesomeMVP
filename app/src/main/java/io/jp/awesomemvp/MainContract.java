package io.jp.awesomemvp;

import android.support.annotation.StringRes;

import io.jp.mvp.IPresenter;
import io.jp.mvp.IView;

/**
 * @author jpwang
 * @since 8/16/16
 */
public interface MainContract {
    interface IMainView extends IView {
        void showToastMessage(String message);
    }

    interface IMainPresenter extends IPresenter<IMainView, Void> {
        void calculatePI(long terms);
    }
}
