package io.jp.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static io.jp.mvp.Util.*;

/**
 * @author jpwang
 * @since 8/16/16
 *
 * A delegate class to build the object graph among the MVP,
 * and you can also use Dagger to make it
 *
 */
public abstract class MVPDelegate<M, V extends IView, P extends IPresenter<V, M>> {
    private V view;
    private P presenter;
    private M model;
    private IModelSerializer<M> serializer;
    private String modelKey;

    public MVPDelegate(@NonNull V view, IModelSerializer<M> modelSerializer) {
        this.view = view;
        this.serializer = modelSerializer;
    }

    /**
     * Bind <code>onCreate</code> lifecycle with view
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link android.app.Activity#onSaveInstanceState(Bundle)}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     * @see android.app.Activity#onCreate(Bundle)
     */
    public final void onCreate(Bundle savedInstanceState) {
        this.presenter = createPresenter();

        modelKey = this.view.getClass().getCanonicalName() + "$Model";
        if (this.serializer != null) {
            this.model = serializer.restoreInstanceState(modelKey, savedInstanceState);
        }
        if (this.model == null) {
            this.model = createModel();
        }
    }

    /**
     * Bind <code>onDestroy</code> lifecycle with view
     *
     * @see Activity#onDestroy()
     */
    public final void onDestroy() {
        checkPresenterNotNull();
        this.presenter.onDestroy();
    }

    /**
     * Bind <code>onStart</code> lifecycle with view
     *
     * @see Activity#onStart()
     */
    public final void onStart() {
        checkPresenterNotNull();
        this.presenter.attachView(this.view, this.model);
    }

    /**
     * Bind <code>onStop</code> lifecycle with view
     *
     * @see Activity#onStop()
     */
    public final void onStop() {
        checkPresenterNotNull();
        this.presenter.detachView();
    }

    /**
     * Bind <code>onSaveInstanceState</code> lifecycle with view
     *
     * @param bundle Bundle in which to place your saved state.
     *
     * @see Activity#onSaveInstanceState(Bundle)
     */
    public final void onSaveInstanceState(Bundle bundle) {
        if (this.model != null) {
            if (this.serializer != null) {
                this.serializer.saveInstanceState(modelKey, this.model, bundle);
            }
        }
    }

    public final P getPresenter() {
        return this.presenter;
    }

    @NonNull
    protected abstract P createPresenter();

    @Nullable
    protected abstract M createModel();

    private void checkPresenterNotNull() {
        checkNotNull(this.presenter, "presenter");
    }
}
