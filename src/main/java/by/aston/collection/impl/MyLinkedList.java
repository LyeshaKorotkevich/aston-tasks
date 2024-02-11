package by.aston.collection.impl;

import by.aston.collection.MyList;
import by.aston.collection.Util;

import java.util.Objects;

public class MyLinkedList<T> implements MyList<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    public MyLinkedList() {
    }

    public MyLinkedList(MyList<? extends T> collection) {
        addAll(collection);
    }

    @Override
    public void add(T element) {
        Node<T> node = new Node<>(tail, element, null);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }

    @Override
    public void add(int index, T element) {
        Util.checkIndex(index, size);

        if (index == size) {
            add(element);
            return;
        }

        Node<T> newNode = new Node<>(null, element, null);

        if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else {
            Node<T> current = getNode(index);
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }

        size++;
    }

    @Override
    public T get(int index) {
        Util.checkIndex(index, size);

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    @Override
    public void remove(int index) {
        Util.checkIndex(index, size);

        Node<T> nodeToDelete = getNode(index);
        if (nodeToDelete == head) {
            head = nodeToDelete.next;
        } else {
            nodeToDelete.prev.next = nodeToDelete.next;
        }
        if (nodeToDelete == tail) {
            tail = nodeToDelete.prev;
        } else {
            nodeToDelete.next.prev = nodeToDelete.prev;
        }
        size--;
    }

    @Override
    public void addAll(MyList<? extends T> elements) {
        for (int i = 0; i < elements.size(); i++) {
            add(elements.get(i));
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T element) {
        Node<T> current = head;
        while (current != null) {
            if (current.item.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private Node<T> getNode(int index) {
        Util.checkIndex(index, size);

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyLinkedList<?> that = (MyLinkedList<?>) o;
        if (size != that.size) return false;
        Node<T> currentThis = head;
        Node<?> currentThat = that.head;
        while (currentThis != null) {
            if (!Objects.equals(currentThis.item, currentThat.item)) return false;
            currentThis = currentThis.next;
            currentThat = currentThat.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        Node<T> current = head;
        while (current != null) {
            hashCode = 31 * hashCode + Objects.hashCode(current.item);
            current = current.next;
        }
        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
