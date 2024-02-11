package by.aston.collection.impl;

import by.aston.collection.MyList;
import by.aston.collection.Util;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList<T> implements MyList<T> {
    private static final int DEFAULT_CAPACITY = 5;
    private int size;
    private T[] array;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity > 0) {
            this.array = (T[]) new Object[capacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(MyList<? extends T> collection) {
        if (!collection.isEmpty()) {
            this.array = (T[]) new Object[collection.size()];
            this.addAll(collection);
        } else {
            this.array = (T[]) new Object[DEFAULT_CAPACITY];
        }
    }

    @Override
    public void add(T element) {
        if (size >= array.length) {
            array = expand();
        }
        array[size++] = element;
    }

    @Override
    public void add(int index, T element) {
        Util.checkIndex(index, size);

        if (size >= array.length) {
            array = expand();
        }

        System.arraycopy(array, index, array, index + 1, size - index);

        array[index] = element;
        size++;
    }

    @Override
    public T get(int index) {
        Util.checkIndex(index, size);
        return array[index];
    }

    @Override
    public void remove(int index) {
        Util.checkIndex(index, size);
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[--size] = null;
    }

    @Override
    public void addAll(MyList<? extends T> elements) {
        if (elements == null || elements.isEmpty()) {
            return;
        }

        ensureCapacity(size + elements.size());

        for (int i = 0; i < elements.size(); i++) {
            add(elements.get(i));
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(element, array[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList<?> that = (MyArrayList<?>) o;
        if (size != that.size) return false;
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(array[i], that.array[i])) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(array[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private T[] expand() {
        T[] newArray = (T[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private void ensureCapacity(int minCapacity) {
        while (minCapacity > array.length) {
            array = expand();
        }
    }
}
