package com.bobocode.cs;

import com.bobocode.cs.exception.EmptyStackException;
import com.bobocode.util.ExerciseNotCompletedException;


/**
 * {@link LinkedStack} is a stack implementation that is based on singly linked generic nodes.
 * A node is implemented as inner static class {@link Node<T>}.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> generic type parameter
 * @author Taras Boychuk
 * @author Serhii Hryhus
 * @implementer Elina Klymovska
 */
public class LinkedStack<T> implements Stack<T> {

    static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    private static Node[] stack = new Node[50];

    {
        if (stack != null && stack.length == size()) {
            Node[] buffer = stack;
            stack = new Node[size() + 50];
            for (int i = 0; i < buffer.length; i++) {
                stack[i] = buffer[i];
            }
        }
    }

    private Node head;
    private int size;


    /**
     * This method creates a stack of provided elements
     *
     * @param elements elements to add
     * @param <T>      generic type
     * @return a new stack of elements that were passed as method parameters
     */
    public static <T> LinkedStack<T> of(T... elements) {
        LinkedStack<T> linkedStack = new LinkedStack<>();
        for (int i = 0; i < elements.length; i++) {
            linkedStack.push(elements[i]);
        }
        return linkedStack;
    }

    /**
     * The method pushes an element onto the top of this stack. This has exactly the same effect as:
     * addElement(item)
     *
     * @param element elements to add
     */
    @Override
    public void push(T element) {
        if (element == null) {
            throw new NullPointerException();
        }

        if (stack[0] != null) {
            Node<T> node = new Node<>(element);
            node.setNext(head);
            head = node;
            size++;
            stack[size()] = node;
        } else {
            stack[0] = new Node<T>(element);
            head = stack[0];
            size = 0;
        }
    }

    /**
     * This method removes the object at the top of this stack
     * and returns that object as the value of this function.
     *
     * @return The object at the top of this stack
     * @throws EmptyStackException - if this stack is empty
     */
    @Override
    public T pop() {
        if (head == null) {
            throw new EmptyStackException();
        } else {
            T object = (T) head.getValue();
            head.setValue(null);
            head =  head.getNext();
            size--;
            return object;
        }
    }

    /**
     * Returns the number of elements in the stack
     *
     * @return number of elements
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if a stack is empty
     *
     * @return {@code true} if a stack is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        if (head == null) {
            return true;
        }else {
            return false;
        }

    }

}
