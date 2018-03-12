package com.owitho.tools.jsc.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author buzhang
 */
public class Context {

    public static class Key<T> {

    }

    public static interface Factory<T> {
        T make();
    }

    private Map<Key, Object> ht = new HashMap<>();

    public <T> void put(Key<T> key, Factory<T> fac) {
        checkState(ht);
        Object old = ht.put(key, fac);
        if (old != null) {
            throw new AssertionError("duplicate context value");
        }
    }

    public <T> void put(Key<T> key, T data) {
        if (data instanceof Factory) {
            throw new AssertionError("T extends Context.Factory");
        }
        checkState(ht);
        Object old = ht.put(key, data);
        if (old != null && !(old instanceof Factory) && old != data && data != null) {
            throw new AssertionError("duplicate context value");
        }
    }

    public <T> T get(Key<T> key) {
        checkState(ht);
        Object o = ht.get(key);
        if (o instanceof Factory) {
            Factory fac = (Factory) o;
            o = fac.make();
            if (o instanceof Factory) {
                throw new AssertionError("T extends Context.Factory");
            }
            assert ht.get(key) == o;
        }

        return Context.uncheckedCast(o);
    }

    public Context() {
    }

    private Map<Class<?>, Key<?>> kt = new HashMap<>();

    private <T> Key<T> key(Class<T> clss) {

    }

    private static <T> T uncheckedCast(Object o) {
        return (T) o;
    }

    private static void checkState(Map<?, ?> t) {
        if (t == null) {
            throw new IllegalStateException();
        }
    }
}
