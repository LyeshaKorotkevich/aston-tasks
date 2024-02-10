package by.aston.collection;

public interface MyList<T> {
    void add(T element);

    T get(int index);

    void remove(int index);

    void addAll(MyList<? extends T> elements);

    void sort();

    int size();

    boolean isEmpty();

    boolean contains(T element);
}
