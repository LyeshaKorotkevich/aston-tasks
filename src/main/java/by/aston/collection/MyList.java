package by.aston.collection;

public interface MyList<T> {
    void add(T element);

    T get(int index);

    void remove(int index);

    boolean addAll(MyList<? extends T> elements);

    void sort();

    int size();
}
