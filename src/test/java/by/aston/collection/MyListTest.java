package by.aston.collection;

import by.aston.collection.impl.MyArrayList;
import by.aston.collection.impl.MyLinkedList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    @Nested
    class TestSort {

        @Test
        void sortShouldSortMyArrayList() {
            // given
            MyList<Integer> actual = new MyArrayList<>();
            actual.add(3);
            actual.add(1);
            actual.add(2);
            actual.add(5);
            actual.add(4);
            MyList<Integer> expected = new MyArrayList<>();
            expected.add(1);
            expected.add(2);
            expected.add(3);
            expected.add(4);
            expected.add(5);

            // when
            MyList.sort(actual);

            // then
            assertEquals(expected, actual);
        }

        @Test
        void sortShouldSortMyLinkedList() {
            // given
            MyList<Integer> actual = new MyLinkedList<>();
            actual.add(3);
            actual.add(1);
            actual.add(2);
            actual.add(5);
            actual.add(4);
            MyList<Integer> expected = new MyLinkedList<>();
            expected.add(1);
            expected.add(2);
            expected.add(3);
            expected.add(4);
            expected.add(5);

            // when
            MyList.sort(actual);

            // then
            assertEquals(expected, actual);
        }
    }
}