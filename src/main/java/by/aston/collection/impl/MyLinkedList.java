package by.aston.collection.impl;

import by.aston.collection.MyList;

public class MyLinkedList<T> implements MyList<T> {

    @Override
    public void add(T element) {
        return false;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public void remove(int index) {
        return false;
    }

    @Override
    public boolean addAll(MyList<? extends T> elements) {
        return false;
    }
}
