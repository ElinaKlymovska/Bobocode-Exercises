package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;
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
        Node<T> first = new Node<>(elements[0]);
        Node<T> last = new Node<>(elements[elements.length - 1]);

        Node<T> current = first;
        for (int i = 1; i < elements.length; i++) {
            Node<T> newNode = new Node<>(elements[i]);
            current.setNext(newNode);
            current = newNode;
        }
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
        if (size == 0) {
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
        if (index < 0) throw new IndexOutOfBoundsException();
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.setNext(first);
            first = newNode;
        } else if (index == size - 1) {
            last.setNext(newNode);
            last = newNode;
        } else {
            Node<T> nodeByIndex = iterateNodes(index - 1);
            newNode.setNext(nodeByIndex.getNext());
            nodeByIndex.setNext(newNode);
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
        if (first == null) throw new IndexOutOfBoundsException();

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
        if (first == null) throw new NoSuchElementException();
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
        if (first == null) throw new NoSuchElementException();
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
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }


    /**
     * Checks if a specific exists in he list
     *
     * @return {@code true} if element exist, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Checks if a list is empty
     *
     * @return {@code true} if list is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Returns the number of elements in the list
     *
     * @return number of elements
     */
    @Override
    public int size() {
        throw new ExerciseNotCompletedException(); // todo: implement this method
    }

    /**
     * Removes all list elements
     */
    @Override
    public void clear() {
        throw new ExerciseNotCompletedException(); // todo: implement this method
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
