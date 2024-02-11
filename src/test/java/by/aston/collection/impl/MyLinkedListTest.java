package by.aston.collection.impl;

import by.aston.collection.MyList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyLinkedListTest {

    @Nested
    class TestConstructors {

        @Test
        void testEmptyConstructor() {
            // given
            MyList<Integer> list = new MyLinkedList<>();

            // then
            assertTrue(list.isEmpty());
            assertEquals(0, list.size());
        }

        @Test
        void testCollectionConstructor() {
            // given
            MyList<Integer> collection = new MyLinkedList<>();
            collection.add(1);
            collection.add(2);
            collection.add(3);

            // when
            MyList<Integer> list = new MyLinkedList<>(collection);

            // then
            assertFalse(list.isEmpty());
            assertEquals(collection.size(), list.size());
            for (int i = 0; i < collection.size(); i++) {
                assertEquals(collection.get(i), list.get(i));
            }
        }
    }

    @Nested
    class TestAdd {

        @Test
        void addShouldIncreaseSize() {
            // given
            MyList<Integer> list = new MyLinkedList<>();

            // when
            list.add(1);

            // then
            assertFalse(list.isEmpty());
            assertEquals(1, list.size());
        }

        @Test
        void addShouldAddElementToEnd() {
            // given
            MyLinkedList<Integer> list = new MyLinkedList<>();

            // when
            list.add(1);

            // then
            assertEquals(1, list.get(0));
        }
    }

    @Nested
    class TestAddAtIndex {

        @Test
        void addAtIndexShouldInsertElement() {
            // given
            MyList<Integer> list = new MyLinkedList<>();
            list.add(1);
            list.add(3);
            int index = 1;
            int elementToAdd = 2;
            int expectedSize = 3;

            // when
            list.add(index, elementToAdd);

            // then
            assertEquals(expectedSize, list.size());
            assertEquals(elementToAdd, list.get(index));
        }

        @Test
        void addAtIndexShouldThrowExceptionForInvalidIndex() {
            // given
            MyList<Integer> list = new MyLinkedList<>();
            list.add(1);
            list.add(2);
            int invalidIndex = 3;
            int elementToAdd = 3;

            // then
            assertThrows(IndexOutOfBoundsException.class, () -> list.add(invalidIndex, elementToAdd));
        }
    }

    @Nested
    class TestGet {

        @Test
        void getShouldReturnElementAtIndex() {
            // given
            MyList<Integer> list = new MyLinkedList<>();
            list.add(1);
            list.add(2);
            list.add(3);

            // then
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
        }

        @Test
        void getShouldThrowExceptionForInvalidIndex() {
            // given
            MyList<Integer> list = new MyLinkedList<>();
            list.add(1);
            list.add(2);
            list.add(3);

            // then
            assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
        }
    }

    @Nested
    class TestRemove {

        @Test
        void removeShouldRemoveElementAtIndex() {
            // given
            MyList<Integer> list = new MyLinkedList<>();
            list.add(1);
            list.add(2);
            list.add(3);

            // when
            list.remove(1);

            // then
            assertEquals(2, list.size());
            assertEquals(1, list.get(0));
            assertEquals(3, list.get(1));
        }

        @Test
        void removeShouldThrowExceptionForInvalidIndex() {
            // given
            MyList<Integer> list = new MyLinkedList<>();
            list.add(1);
            list.add(2);
            list.add(3);

            // then
            assertThrows(IndexOutOfBoundsException.class, () -> list.remove(3));
        }
    }

    @Nested
    class TestAddAll {

        @Test
        void addAllShouldAddAllElementsFromCollection() {
            // given
            MyList<Integer> list = new MyLinkedList<>();
            MyList<Integer> collection = new MyArrayList<>();
            collection.add(1);
            collection.add(2);
            collection.add(3);

            // when
            list.addAll(collection);

            // then
            assertEquals(3, list.size());
            assertEquals(1, list.get(0));
            assertEquals(2, list.get(1));
            assertEquals(3, list.get(2));
        }
    }

    @Nested
    class TestIsEmpty {

        @Test
        void isEmptyShouldReturnTrueForEmptyList() {
            // given
            MyList<Integer> list = new MyLinkedList<>();

            // then
            assertTrue(list.isEmpty());
        }

        @Test
        void isEmptyShouldReturnFalseForNonEmptyList() {
            // given
            MyList<Integer> list = new MyLinkedList<>();
            list.add(1);

            // then
            assertFalse(list.isEmpty());
        }
    }

    @Nested
    class TestContains {

        @Test
        void containsShouldReturnTrueIfElementExists() {
            // given
            MyList<Integer> list = new MyLinkedList<>();
            list.add(1);
            list.add(2);
            list.add(3);

            // then
            assertTrue(list.contains(2));
        }

        @Test
        void containsShouldReturnFalseIfElementDoesNotExist() {
            // given
            MyList<Integer> list = new MyLinkedList<>();
            list.add(1);
            list.add(2);
            list.add(3);

            // then
            assertFalse(list.contains(4));
        }
    }

    @Nested
    class TestEquals {

        @Test
        void equalsShouldReturnTrueForEqualLists() {
            // given
            MyList<Integer> list1 = new MyLinkedList<>();
            list1.add(1);
            list1.add(2);
            list1.add(3);
            MyList<Integer> list2 = new MyLinkedList<>();
            list2.add(1);
            list2.add(2);
            list2.add(3);

            // then
            assertEquals(list1, list2);
        }

        @Test
        void equalsShouldReturnFalseForDifferentLists() {
            // given
            MyList<Integer> list1 = new MyLinkedList<>();
            list1.add(1);
            list1.add(2);
            MyList<Integer> list2 = new MyLinkedList<>();
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
            MyList<Integer> list1 = new MyLinkedList<>();
            list1.add(1);
            list1.add(2);
            list1.add(3);
            MyList<Integer> list2 = new MyLinkedList<>();
            list2.add(1);
            list2.add(2);
            list2.add(3);

            // then
            assertEquals(list1.hashCode(), list2.hashCode());
        }
    }
}