package by.aston.collection;

public class Util {
    private Util() {

    }

    public static void checkIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index);
        }
    }
}
