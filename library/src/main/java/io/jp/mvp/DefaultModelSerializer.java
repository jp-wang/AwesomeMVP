package io.jp.mvp;

import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author jpwang
 * @since 8/16/16
 */
public class DefaultModelSerializer<M> implements IModelSerializer<M> {
    @Override
    public void saveInstanceState(String key, M model, Bundle savedState) {
        if (savedState == null || model == null || key == null) {
            return;
        }
        if (model instanceof Parcelable) {
            savedState.putParcelable(key, (Parcelable) model);
        } else if (model instanceof Serializable) {
            savedState.putSerializable(key, (Serializable) model);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public M restoreInstanceState(String key, Bundle savedState) {
        if (savedState != null && key != null) {
            Object instance = savedState.get(key);
            try {
                return (M) instance;
            } catch (ClassCastException e) {
                throw new IllegalStateException(String.format("The model with key(%s) we are expecting to restore is not matching the real type in-store(%s)."
                        , key, instance));
            }
        }
        return null;
    }
}
