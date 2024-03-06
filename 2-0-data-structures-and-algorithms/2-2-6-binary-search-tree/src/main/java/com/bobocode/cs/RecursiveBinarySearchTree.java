package com.bobocode.cs;

import com.bobocode.util.ExerciseNotCompletedException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 * <p><p>
 * <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 * <p>
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
@AllArgsConstructor
@NoArgsConstructor
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private Node<T> root;
    private int size;

    @Getter
    @Setter
    @NoArgsConstructor
    private static class Node<T> {
        private T value;
        private Node<T> right;
        private Node<T> left;

        public Node(T value) {
            this.value = value;
        }
    }

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        Node<T>[] nodes = Arrays.stream(elements).sorted().map(Node::new).toArray(Node[]::new);

        int mid = nodes.length / 2;
        Node<T> root = nodes[mid];

        Node<T> head = root;

        for (int i = 0; i < mid; i++) {
            head.setLeft(nodes[i]);
            head = head.getLeft();
        }

        head = root;

        for (int i = mid + 1; i < nodes.length; i++) {
            head.setRight(nodes[i]);
            head = head.getRight();
        }

        return new RecursiveBinarySearchTree<>(root, elements.length);
    }

    @Override
    public boolean insert(T element) {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public boolean contains(T element) {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public int size() {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public int depth() {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        throw new ExerciseNotCompletedException();
    }
}
