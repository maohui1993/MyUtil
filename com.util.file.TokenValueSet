package com.util.file;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by hooyee on 2016/5/26.
 * e-mail maohui_dream@outlook.com
 */
public class TokenValueSet implements Cloneable {

    private final Set<TokenValue> values;

    public TokenValueSet() {
        values = new HashSet<TokenValue>();
    }

    public TokenValueSet(final Set<TokenValue> values) {
        this.values = new HashSet<TokenValue>();
        this.values.addAll(values);
    }

    public void add(final TokenValue tokenValue) {
        boolean added = this.values.add(tokenValue);
    }

    public void addAll(final Set<TokenValue> more) {
        this.values.addAll(more);
    }

    public void remove(final TokenValue tokenValue) {
        this.values.remove(tokenValue);
    }

    public void clear() {
        this.values.clear();
    }

    public Iterator<TokenValue> iterator() {
        return this.values.iterator();
    }

    public boolean isEmpty() {
        return this.values.isEmpty();
    }

    public Object[] toArray() {
        return this.values.toArray();
    }

    public int size() {
        return this.values.size();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString() {
        StringBuffer buffer = new StringBuffer();
        Iterator<TokenValue> iterator = this.iterator();
        while (iterator.hasNext()) {
            TokenValue tokenValue = iterator.next();
            buffer.append(tokenValue.toString());
            buffer.append(System.getProperty("line.separator"));
        }
        return buffer.toString();
    }
}
