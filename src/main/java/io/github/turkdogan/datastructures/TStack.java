package io.github.turkdogan.datastructures;

/**
   A simple stack implementation with Java Optional

   Turkdogan Tasdelen ttasdelen@gmail.com
*/
import java.util.Optional;

import java.io.Serializable;

public class TStack<T> implements TCollection {

    private Optional<TStackNode<T>> node;

    private int size;

    public TStack() {
        node = Optional.empty();
    }

    public void push(T element) {
        TStackNode<T> newNode = new TStackNode<T>(element);
        newNode.setNext(this.node);
        this.node = Optional.of(newNode);

        size++;
    }

    public Optional<T> pop() {
        if (node.isPresent()) {
            size--;

            TStackNode<T> tempNode = node.get();
            this.node = this.node.get().getNext();
            return Optional.of(tempNode.getElement());
        } else {
            return Optional.empty();
        }
    }

    public Optional<T> peek() {
        if (node.isPresent()) {
            return Optional.of(this.node.get().getElement());
        }
        return Optional.empty();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private class TStackNode<T> implements Serializable {

        private T element;

        private Optional<TStackNode<T>> next;

        public TStackNode(T element) {
            setElement(element);
            setNext(Optional.empty());
        }

        public T getElement() {
            return this.element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Optional<TStackNode<T>> getNext() {
            return this.next;
        }

        public void setNext(Optional<TStackNode<T>> next) {
            this.next = next;
        }
    }
}
