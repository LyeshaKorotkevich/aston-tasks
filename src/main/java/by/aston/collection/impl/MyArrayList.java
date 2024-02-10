package by.aston.collection.impl;

import by.aston.collection.MyList;

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
    public T get(int index) {
        checkIndex(index, size);
        return array[index];
    }

    @Override
    public void remove(int index) {
        checkIndex(index, size);
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

    @SuppressWarnings("unchecked")
    public void sort() {
        boolean notSorted = true;
        for (int i = 0; i < array.length - 1 && notSorted; i++) {
            notSorted = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (((Comparable<? super T>) array[j]).compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    notSorted = true;
                }
            }
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
        return size == that.size && Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @SuppressWarnings("unchecked")
    private T[] expand() {
        T[] newArray = (T[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        return newArray;
    }

    private void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    private void ensureCapacity(int minCapacity) {
        while (minCapacity > array.length) {
            array = expand();
        }
    }
}
