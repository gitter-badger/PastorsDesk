package com.mabivavalon.pastorsdesk.Util;

/**
 * Created by Darnell on 3/15/2015.
 */
public class _<E> {
    E ref;
    public _( E e ){
        ref = e;
    }
    public E g() { return ref; }
    public void s( E e ){ this.ref = e; }

    public String toString() {
        return ref.toString();
    }
}
