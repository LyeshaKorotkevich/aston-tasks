package by.aston.collection;

public interface MyList<T> {
    void add(T element);
    void add(int index, T element);

    T get(int index);

    void remove(int index);

    void addAll(MyList<? extends T> elements);

    int size();

    boolean isEmpty();

    boolean contains(T element);

    static <T extends Comparable<? super T>> void sort(MyList<T> list) {
        boolean notSorted = true;
        for (int i = 0; i < list.size() - 1 && notSorted; i++) {
            notSorted = false;
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    T temp = list.get(j);
                    list.add(j, list.get(j + 1));
                    list.remove(j + 1);
                    list.add(j + 1, temp);
                    list.remove(j + 2);
                    notSorted = true;
                }
            }
        }
    }
}
