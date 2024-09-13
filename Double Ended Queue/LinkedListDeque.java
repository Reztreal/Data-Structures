package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T _item, Node _next, Node _prev) {
            item = _item;
            next = _next;
            prev = _prev;
        }
    }

    private int size = 1;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        size += 1;

        Node n = new Node(item, sentinel, sentinel);
        Node tmp = sentinel.next;

        n.next = tmp;
        tmp.prev = n;
        n.prev = sentinel;
        sentinel.next = n;
    }

    public void addLast(T item) {
        size += 1;

        Node tmp = sentinel.prev;
        Node n = new Node(item, sentinel, tmp);
        tmp.next = n;
        sentinel.prev = n;
    }

    public T removeFirst() {
        if (isEmpty())
            return null;

        size -= 1;

        T val = sentinel.next.item;
        Node secondNode = sentinel.next.next;

        sentinel.next = secondNode;
        secondNode.prev = sentinel;

        return val;
    }

    public T removeLast() {
        if (isEmpty())
            return null;

        size -= 1;

        Node lastNode = sentinel.prev;
        T val = lastNode.item;

        Node newLastNode = lastNode.prev;

        sentinel.prev = newLastNode;
        newLastNode.next = sentinel;

        return val;
    }

    public T get(int index) {
        if (isEmpty() || index > size()) return null;

        Node n = sentinel;

        for (int i = 0; i <= index; i++) {
            n = n.next;
        }

        return n.item;
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        LinkedListDeque<?> otherList = (LinkedListDeque<?>) o;  // Cast after type check
        if (otherList.size() != this.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (!otherList.get(i).equals(this.get(i))) {  // Use equals for object comparison
                return false;
            }
        }

        return true;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel, index + 1);
    }

    private T getRecursiveHelper(Node n, int index) {
        if (index == 0) {
            return n.item;
        }

        return getRecursiveHelper(n.next, index - 1);
    }

    public int size() {
        return size - 1;
    }

    public void printDeque() {
        Node n = sentinel.next;
        int i = 0;

        while (i <= size()) {
            System.out.print(" <- " + n.item + " -> ");

            i += 1;
            n = n.next;
        }

        System.out.println();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        int pos;

        public LinkedListDequeIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return pos < size();
        }

        public T next() {
            T item = get(pos);
            pos++;

            return item;
        }
    }

    public static void main(String[] args) {

    }
}
