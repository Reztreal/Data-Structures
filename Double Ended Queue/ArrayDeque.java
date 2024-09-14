package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;

    private int nextFirst = 4;
    private int nextLast = 5;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    public void resizeUp() {
        T[] new_array = (T[]) new Object[items.length * 2];

        int index = 4;
        for (int i = getPosition(nextFirst + 1); i < totalLength() + getPosition(nextFirst + 1); i++) {
            new_array[index] = getCircular(i);
            index += 1;
        }
        nextFirst = 3;
        nextLast = nextFirst + items.length + 1;

        items = new_array;
    }

    public void resizeDown() {
        T[] new_array = (T[]) new Object[items.length / 2];

        int index = 2;
        for (int i = getPosition(nextFirst + 1); i < getPosition(nextFirst + 1) + items.length / 4; i++) {
            new_array[index] = getCircular(i);
            index += 1;
        }

        nextFirst = 1;
        nextLast = 1 + nextFirst + items.length / 4;

        items = new_array;
    }

    public void addFirst(T item) {
        size += 1;

        if (usageRatio() > 1.0)
            resizeUp();

        int pos = getPosition(nextFirst);
        items[pos] = item;

        nextFirst = getPosition(nextFirst - 1);
    }

    public T removeFirst() {
        if (isEmpty())
            return null;

        size -= 1;

        T val;

        int pos = getPosition(nextFirst + 1);
        val = items[pos];
        items[pos] = null;

        nextFirst = getPosition(nextFirst + 1);

        if (usageRatio() < 0.25 && items.length >= 16)
            resizeDown();

        return val;
    }

    public void addLast(T item) {
        size += 1;

        if (usageRatio() > 1.0)
            resizeUp();

        int pos = getPosition(nextLast);
        items[pos] = item;

        nextLast = getPosition(nextLast + 1);
    }

    public T removeLast() {
        if (isEmpty())
            return null;

        size -= 1;

        T val;

        int pos = getPosition(nextLast - 1);
        val = items[pos];
        items[pos] = null;

        nextLast = getPosition(nextLast - 1);

        if (usageRatio() < 0.25 && items.length >= 16)
            resizeDown();

        return val;
    }

    private float usageRatio() {
        return (float) size / items.length;
    }

    public int getPosition(int n) {
        if (n >= items.length) {
            return n - items.length;
        }
        else if (n < 0) {
            return items.length + n;
        }

        return n;
    }

    public T get(int index) {
        if (index < 0 || index >= items.length)
            return null;

        return items[nextFirst + index + 1];
    }

    public T getCircular(int index) {
        int i = getPosition(index);
        return items[i];
    }

    public int size() {
        return size;
    }

    public int totalLength() {
        return items.length;
    }

    public void printDeque() {
        System.out.println("0 1 2 3 4 5 6 7");
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.print(items[i] + " ");
            }
            else
                System.out.print("_ ");
        }
        System.out.println();
        System.out.println("Next First: " + nextFirst + " Next Last: " + nextLast);
        System.out.println();
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) return false;

        ArrayDeque<?> otherDq = (ArrayDeque<?>) o;
        if (otherDq.size() != this.size()) {
            return false;
        }

        for (int i = 0; i < items.length; i++) {
            if (!otherDq.getCircular(i).equals(this.getCircular(i))) {
                return false;
            }
        }

        return true;
    }


    private class ArrayDequeIterator implements Iterator<T> {
        int pos;

        public ArrayDequeIterator() {
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
}
