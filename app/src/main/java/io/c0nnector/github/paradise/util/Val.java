package io.c0nnector.github.paradise.util;

/**
 * Util class to check values
 */
public class Val {

    /**
     * True if not null
     * @param t object to be checked
     * @param <T>
     * @return
     */
    public static <T>boolean notNull(T t){
        return t !=null;
    }

    /**
     * True if null
     * @param t object to be checked
     * @param <T>
     * @return
     */
    public static <T>boolean isNull(T t){
        return t == null;
    }

    /**
     * Checks if all list objects are non null
     * @param objects
     * @return
     *
     * P.S we don't use noNull() for performance reasons
     */
    public static boolean containsNull(Object... objects) {

        for (Object object: objects) {

            if (object == null) return true;
        }

        return false;
    }
}
