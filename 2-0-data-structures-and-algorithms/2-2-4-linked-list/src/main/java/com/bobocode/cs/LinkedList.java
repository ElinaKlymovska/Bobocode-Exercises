package com.bobocode.cs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.NoSuchElementException;

/**
 * {@link LinkedList} is a list implementation that is based on singly linked generic nodes. A node is implemented as
 * inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LinkedList<T> implements List<T> {
    @Getter
    @Setter
    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    /**
     * This method creates a list of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new list of elements the were passed as method parameters
     */
    public static <T> LinkedList<T> of(T... elements) {
        if (elements.length == 0) {
            throw new IllegalArgumentException("Cannot create a LinkedList with no elements");
        }

        Node<T> first = new Node<>(elements[0]);
        Node<T> current = first;

        for (int i = 1; i < elements.length; i++) {
            Node<T> newNode = new Node<>(elements[i]);
            current.setNext(newNode);
            current = newNode;
        }

        Node<T> last = current;

        return new LinkedList<>(first, last, elements.length);
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element element to add
     */
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);

        if (isEmpty()) {
            first = newNode;
            last = first;
        } else {
            last.setNext(newNode);
            last = newNode;
        }

        size++;
    }

    /**
     * Adds a new element to the specific position in the list. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an index of new element
     * @param element element to add
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Node<T> newNode = new Node<>(element);

        if (index == 0) {
            newNode.setNext(first);
            first = newNode;

            if (size == 0) {
                last = newNode;
            }
        } else {
            Node<T> nodeBeforeIndex = iterateNodes(index - 1);
            newNode.setNext(nodeBeforeIndex.getNext());
            nodeBeforeIndex.setNext(newNode);

            if (index == size) {
                last = newNode;
            }
        }
        size++;
    }

    /**
     * Changes the value of an list element at specific position. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index   an position of element to change
     * @param element a new element value
     */
    @Override
    public void set(int index, T element) {
        if (isEmpty()) throw new IndexOutOfBoundsException("Cannot set elements in empty list");

        Node<T> nodeByIndex = iterateNodes(index);
        nodeByIndex.setValue(element);
    }

    /**
     * Retrieves an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return an element value
     */
    @Override
    public T get(int index) {
        return iterateNodes(index).getValue();
    }

    /**
     * Returns the first element of the list. Operation is performed in constant time O(1)
     *
     * @return the first element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getFirst() {
        if (isEmpty()) throw new NoSuchElementException("The list is empty");
        return first.getValue();
    }

    /**
     * Returns the last element of the list. Operation is performed in constant time O(1)
     *
     * @return the last element of the list
     * @throws java.util.NoSuchElementException if list is empty
     */
    @Override
    public T getLast() {
        if (isEmpty()) throw new NoSuchElementException("The list is empty");
        return last.getValue();
    }

    /**
     * Removes an elements by its position index. In case provided index in out of the list bounds it
     * throws {@link IndexOutOfBoundsException}
     *
     * @param index element index
     * @return deleted element
     */
    @Override
    public T remove(int index) {
        if (isEmpty()) throw new IndexOutOfBoundsException("Cannot remove elements in empty list");

        T removedValue;

        if (index == 0) {
            removedValue = first.getValue();
            first = first.getNext();
            if (first == null) {
                last = null;
            }
        } else {
            Node<T> nodeBeforeIndex = iterateNodes(index - 1);
            if (nodeBeforeIndex.getNext() == last) {
                last = nodeBeforeIndex;
            }
            removedValue = nodeBeforeIndex.getNext().getValue();
            nodeBeforeIndex.setNext(nodeBeforeIndex.getNext().getNext());
        }

        size--;
        return removedValue;
    }

    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        Node<T> current = first;
        while (current != null) {
            if (current.getValue().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    private Node<T> iterateNodes(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }
}
