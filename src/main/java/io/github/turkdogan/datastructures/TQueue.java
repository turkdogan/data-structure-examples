package io.github.turkdogan.datastructures;

/**
   A simple Queue implementation with Java Optional

   Turkdogan Tasdelen ttasdelen@gmail.com
*/

import java.util.Optional;

public class TQueue<T> implements TCollection {

    private int size;

    private Optional<TQueueNode<T>> head;
    private Optional<TQueueNode<T>> tail;

    public TQueue() {
        head = tail = Optional.empty();
    }

    public void enqueue(final T element) {
        TQueueNode<T> node = new TQueueNode<T>(element);
        Optional<TQueueNode<T>> optNode = Optional.of(node);
        if (head.isPresent()) {
            node.setNext(this.head);
            this.head.get().setPrevious(optNode);
            this.head = optNode;
        } else {
            this.head = this.tail = optNode;
        }
        size++;
    }

    public Optional<T> dequeue() {
        if (tail.isPresent()) {
            Optional<T> value = Optional.of(tail.get().getElement());
            tail = tail.get().getPrevious();
            if (size == 1) {
                head = tail;
            }
            size--;
            return value;
        }
        return Optional.empty();
    }

    public Optional<T> peek() {
        if (tail.isPresent()) {
            return Optional.of(tail.get().getElement());
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

    private class TQueueNode<T> {

        private T element;

        private Optional<TQueueNode<T>> next;
        private Optional<TQueueNode<T>> previous;

        public TQueueNode(final T element) {
            setElement(element);
            setNext(Optional.empty());
            setPrevious(Optional.empty());
        }

        public T getElement() {
            return this.element;
        }

        public void setElement(final T element) {
            this.element = element;
        }

        public Optional<TQueueNode<T>> getNext() {
            return this.next;
        }

        public void setNext(final Optional<TQueueNode<T>> next) {
            this.next = next;
        }

        public Optional<TQueueNode<T>> getPrevious() {
            return this.previous;
        }

        public void setPrevious(final Optional<TQueueNode<T>> previous) {
            this.previous = previous;
        }
    }
}
