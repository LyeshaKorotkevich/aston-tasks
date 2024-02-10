package by.aston.collection.impl;

import by.aston.collection.MyList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    @Nested
    class TestConstructors {

        @Test
        void testNegativeCapacityConstructor() {
            assertThrows(IllegalArgumentException.class, () -> new MyArrayList<>(-5));
        }

        @Test
        void testCollectionConstructor() {
            // given
            MyList<Integer> collection = new MyArrayList<>();
            collection.add(1);
            collection.add(2);
            collection.add(3);

            // when
            MyList<Integer> list = new MyArrayList<>(collection);

            // then
            assertEquals(collection.size(), list.size());
            for (int i = 0; i < collection.size(); i++) {
                assertEquals(collection.get(i), list.get(i));
            }
        }
    }

    @Nested
    class TestAdd {

        @Test
        void addShouldNotExpandArray() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>();
            int expected = 1;

            // when
            list.add(2);
            int actual = list.size();

            // then
            assertEquals(expected, actual);
        }

        @Test
        void addShouldExpandArray() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>(3);
            int expected = 4;

            // when
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            int actual = list.size();

            // then
            assertEquals(expected, actual);
        }
    }

    @Nested
    class TestGet {

        @Test
        void getShouldReturnInteger() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            int expected = 3;

            // when
            int actual = list.get(2);

            // then
            assertEquals(expected, actual);
        }

        @Test
        void getShouldThrowException() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>(3);

            // when, then
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        }
    }

    @Nested
    class TestRemove {

        @Test
        void removeShouldRemoveElementInTheMiddle() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            int expectedSize = 2;

            // when
            list.remove(1);

            // then
            assertEquals(expectedSize, list.size());
            assertEquals(3, list.get(1));
        }

        @Test
        void removeShouldRemoveElementInTheEnd() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            int expectedSize = 2;

            // when
            list.remove(2);

            // then
            assertEquals(expectedSize, list.size());
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(2));
        }

        @Test
        void removeShouldRemoveElementInTheStart() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            int expectedSize = 2;

            // when
            list.remove(0);

            // then
            assertEquals(expectedSize, list.size());
            assertEquals(2, list.get(0));
        }
    }

    @Nested
    class TestAddAll {

        @Test
        void addAllShouldAddElementsFromCollection() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>();
            MyArrayList<Integer> collection = new MyArrayList<>();
            collection.add(1);
            collection.add(2);
            collection.add(3);
            int expectedSize = 3;

            // when
            list.addAll(collection);

            // then
            assertEquals(expectedSize, list.size());
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
        }
    }

    @Nested
    class TestSort {

        @Test
        void sortShouldSortElements() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>();
            list.add(3);
            list.add(1);
            list.add(2);
            list.add(5);
            list.add(4);
            MyArrayList<Integer> expectedList = new MyArrayList<>();
            expectedList.add(1);
            expectedList.add(2);
            expectedList.add(3);
            expectedList.add(4);
            expectedList.add(5);

            // when
            list.sort();

            // then
            assertEquals(expectedList, list);
        }
    }

    @Nested
    class TestIsEmpty {
        @Test
        void isEmptyShouldReturnTrue() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>();
            boolean expected = true;

            // when
            boolean actual = list.isEmpty();

            // then
            assertEquals(expected, actual);
        }

        @Test
        void isEmptyShouldReturnFalse() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>();
            list.add(1);
            boolean expected = false;

            // when
            boolean actual = list.isEmpty();

            // then
            assertEquals(expected, actual);
        }
    }

    @Nested
    class TestContains {
        @Test
        void containsShouldReturnTrue() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            boolean expected = true;

            // when
            boolean actual = list.contains(1);

            // then
            assertEquals(expected, actual);
        }

        @Test
        void containsShouldReturnFalse() {
            // given
            MyArrayList<Integer> list = new MyArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            boolean expected = false;

            // when
            boolean actual = list.contains(4);

            // then
            assertEquals(expected, actual);
        }
    }

    @Nested
    class TestEquals {

        @Test
        void equalsShouldReturnTrueForEqualLists() {
            // given
            MyArrayList<Integer> list1 = new MyArrayList<>();
            list1.add(1);
            list1.add(2);
            list1.add(3);
            MyArrayList<Integer> list2 = new MyArrayList<>();
            list2.add(1);
            list2.add(2);
            list2.add(3);

            // then
            assertEquals(list1, list2);
        }

        @Test
        void equalsShouldReturnFalseForDifferentLists() {
            // given
            MyArrayList<Integer> list1 = new MyArrayList<>();
            list1.add(1);
            list1.add(2);
            MyArrayList<Integer> list2 = new MyArrayList<>();
            list2.add(1);
            list2.add(3);

            // then
            assertNotEquals(list1, list2);
        }
    }

    @Nested
    class TestHashCode {

        @Test
        void hashCodeShouldBeEqualForEqualLists() {
            // given
            MyArrayList<Integer> list1 = new MyArrayList<>();
            list1.add(1);
            list1.add(2);
            list1.add(3);
            MyArrayList<Integer> list2 = new MyArrayList<>();
            list2.add(1);
            list2.add(2);
            list2.add(3);

            // then
            assertEquals(list1.hashCode(), list2.hashCode());
        }
    }
}