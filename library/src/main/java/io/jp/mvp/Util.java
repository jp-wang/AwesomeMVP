package io.jp.mvp;

/**
 * @author jpwang
 * @since 8/16/16
 */
public final class Util {
    private Util() {

    }

    /**
     *
     * @param instance The instance will be checked
     * @param name If the instance is Null, the name will be appended into the error message
     * @param <T> Instance type
     * @return the original instance
     */
    public static <T> T checkNotNull(T instance, String name) {
        if (instance == null) {
            throw new IllegalStateException(name + " cannot be null!");
        }
        return instance;
    }
}
