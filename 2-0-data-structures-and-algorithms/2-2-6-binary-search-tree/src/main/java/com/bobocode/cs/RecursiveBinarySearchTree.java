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
        if (elements == null || elements.length == 0) throw new NullPointerException();

        Node<T>[] nodes = createSortedNodes(elements);

        Node<T> root = nodes[nodes.length / 2];
        buildBinarySearchTree(root, nodes);
        return new RecursiveBinarySearchTree<>(root, elements.length);
    }

    private static <T extends Comparable<T>> void buildBinarySearchTree(Node<T> parent, Node<T>[] nodes) {
        switch (nodes.length) {
            case 1 -> {
                //do nothing
            }
            case 2 -> setChildNode(parent, nodes[1], parent.getValue().compareTo(nodes[1].getValue()) > 0);
            case 3 -> {
                parent.setLeft(nodes[0]);
                parent.setRight(nodes[2]);
            }
            default -> buildSubTree(parent, nodes);
        }
    }

    private static <T extends Comparable<T>> void setChildNode(Node<T> parent, Node<T> child, boolean isLeft) {
        if (isLeft) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }
    }

    private static <T extends Comparable<T>> void buildSubTree(Node<T> parent, Node<T>[] nodes) {
        int mid = nodes.length / 2;

        Node<T> left = nodes[mid / 2];
        parent.setLeft(left);

        Node<T> right = nodes[mid + (mid / 2)];
        parent.setRight(right);

        buildBinarySearchTree(left, Arrays.copyOfRange(nodes, 0, mid));
        buildBinarySearchTree(right, Arrays.copyOfRange(nodes, mid + (mid / 2), nodes.length));
    }

    private static <T extends Comparable<T>> Node<T>[] createSortedNodes(T[] elements) {
        return Arrays.stream(elements)
                .sorted()
                .distinct()
                .map(Node<T>::new)
                .toArray(Node[]::new);
    }

    @Override
    public boolean insert(T element) {
        throw new ExerciseNotCompletedException();
    }

    @Override
    public boolean contains(T element) {
        if (size == 0) return false;
        if (element == null) throw new NullPointerException();

        Node<T> head = root;
        for (int i = 0; i < size; i++) {
            if (element.equals(head.getValue())) return true;
            if (element.compareTo(head.getValue()) < 0) {
                head = head.getLeft();
            } else {
                head = head.getRight();
            }
        }
        return false;
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
