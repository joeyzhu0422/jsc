package com.owitho.tools.jsc.util;

import java.util.AbstractQueue;
import java.util.Iterator;

/**
 * @author buzhang
 */
public class ListBuffer<A> extends AbstractQueue<A> {
    public Iterator<A> iterator() {
        return null;
    }

    public int size() {
        return 0;
    }

    public boolean offer(A a) {
        return false;
    }

    public A poll() {
        return null;
    }

    public A peek() {
        return null;
    }
}
