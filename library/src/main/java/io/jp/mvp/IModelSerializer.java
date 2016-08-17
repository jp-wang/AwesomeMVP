package io.jp.mvp;

import android.os.Bundle;

/**
 * @author jpwang
 * @since 8/16/16
 *
 * Serialize and Deserialize the model.
 *
 * M - Model
 *
 */
public interface IModelSerializer<M> {
    void saveInstanceState(String key, M model, Bundle savedState);

    M restoreInstanceState(String key, Bundle savedState);
}
