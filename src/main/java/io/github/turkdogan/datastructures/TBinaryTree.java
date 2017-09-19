package io.github.turkdogan.datastructures;

/**
   A simple binary tree implementation

   Turkdogan Tasdelen ttasdelen@gmail.com
*/
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import java.lang.Comparable;

public class TBinaryTree<T extends Comparable<T>> implements TCollection {

    private Optional<TTreeNode<T>> root;

    private int size;

    public TBinaryTree() {
        root = Optional.empty();
    }

    public void insert(final T value) {
        Optional<TTreeNode<T>> node = root;
        Optional<TTreeNode<T>> temp = Optional.empty();
        while(node.isPresent()) {
            temp = node;
            node = (value.compareTo(node.get().value) < 0)
                ? node.get().left
                : node.get().right;
        }
        if (temp.isPresent()) {
            if (value.compareTo(temp.get().value) < 0) {
                // create a left leaf node
                TTreeNode<T> leftNode = new TTreeNode(value);
                temp.get().left = Optional.of(leftNode);
            } else {
                TTreeNode<T> rightNode = new TTreeNode(value);
                temp.get().right = Optional.of(rightNode);
            }
        } else {
            // could not any node, insert to root
            root = Optional.of(new TTreeNode(value));
        }
        size++;
    }

    public boolean search(final T value) {
        Optional<TTreeNode<T>> node = root;
        while(node.isPresent()) {
            int compare = value.compareTo(node.get().value);
            if (compare == 0) {
                return true;
            }
            node = (compare < 0) ? node.get().left : node.get().right;
        }
        return false;
    }

    public List<T> inOrder() {
        final List<T> list = new ArrayList<>();
        if (this.root.isPresent()) {
            inOrder(this.root.get(), list);
        }
        return list;
    }

    private void inOrder(final TTreeNode<T> node, final List<T> buffer) {
        node.left.ifPresent(left -> inOrder(left, buffer));
        buffer.add(node.value);
        node.right.ifPresent(right -> inOrder(right, buffer));
    }

    public List<T> preOrder() {
        final List<T> list = new ArrayList<>();
        if (this.root.isPresent()) {
            preOrder(this.root.get(), list);
        }
        return list;
    }

    private void preOrder(final TTreeNode<T> node, final List<T> buffer) {
        buffer.add(node.value);
        node.left.ifPresent(left -> preOrder(left, buffer));
        node.right.ifPresent(right -> preOrder(right, buffer));
    }

    public List<T> postOrder() {
        final List<T> list = new ArrayList<>();
        if (this.root.isPresent()) {
            postOrder(this.root.get(), list);
        }
        return list;
    }

    private void postOrder(final TTreeNode<T> node, final List<T> buffer) {
        buffer.add(node.value);
        node.left.ifPresent(left -> postOrder(left, buffer));
        node.right.ifPresent(right -> postOrder(right, buffer));
    }

    public boolean delete(final T value) {
        if (!root.isPresent()) {
            return false;
        }
        Optional<TTreeNode<T>> node = root;
        Optional<TTreeNode<T>> parent = Optional.empty();
        while(node.isPresent()) {
            int compare = value.compareTo(node.get().value);
            if (compare == 0) {
                size--;
                if (size == 0) { // delete root
                    root = Optional.empty();
                    return true;
                }
                if (node == root ||
                        (node.get().left.isPresent() && node.get().right.isPresent())) {
                    replaceSuccessor(node.get());
                } else {
                    deleteNode(node.get(), parent.get());
                }
                return true;
            }
            parent = node;

            node = (value.compareTo(node.get().value) < 0)
                ? node.get().left
                : node.get().right;
        }
        return false;
    }

    private void replaceSuccessor(TTreeNode<T> node) {
        boolean navigateRight = node.right.isPresent();

        // 1. find minimum node from right subtree,
        // 2. replace it with the deleted node
        // 3. delete the minimum subtree node
        if (navigateRight) {
            TTreeNode<T> minimum = node.right.get();
            TTreeNode<T> minimumParent = node;
            while (minimum.left.isPresent()) {
                minimumParent = minimum;
                minimum = minimum.left.get();
            }
            node.value = minimum.value;
            if (minimumParent.left.isPresent())  {
                if (minimum.right.isPresent()) {
                    minimumParent.left = minimum.right;
                } else {
                    minimumParent.left = Optional.empty();
                }
            } else {
                minimumParent.left = minimum.right;
            }
        } else {
            TTreeNode<T> minimum = node.left.get();
            TTreeNode<T> minimumParent = node;
            while (minimum.right.isPresent()) {
                minimumParent = minimum;
                minimum = minimum.right.get();
            }
            node.value = minimum.value;
            if (minimumParent.right.isPresent())  {
                if (minimum.right.isPresent()) {
                    minimumParent.right = minimum.right;
                } else {
                    minimumParent.right = Optional.empty();
                }
            } else {
                minimumParent.right = minimum.right;
            }
        }
    }

    private void deleteNode(TTreeNode<T> node, TTreeNode<T> parent) {
        if (!node.left.isPresent() && !node.right.isPresent()) { // leaf node?
            if (parent.left.isPresent() && parent.left.get() == node) {
                parent.left = Optional.empty();
            } else {
                parent.right = Optional.empty();
            }
        } else if (!node.left.isPresent()) {
            if (parent.left.isPresent() && parent.left.get() == node) {
                parent.left = node.right;
            } else {
                parent.right = node.right;
            }
        } else { //if (!node.right.isPresent())
            if (parent.left.get() == node) {
                parent.left = node.left;
            } else {
                parent.right = node.left;
            }
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private Optional<TTreeNode<T>> searchNode(final T value) {
        return Optional.empty();
    }

    private class TTreeNode<T extends Comparable<T>> {

        T value;

        Optional<TTreeNode<T>> left;

        Optional<TTreeNode<T>> right;

        public TTreeNode(final T value) {
            this.value = value;
            left = right = Optional.empty();
        }

    }
}
