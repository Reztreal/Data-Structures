package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        comp = c;
    }

    public T max() {
        if (isEmpty()) return null;

        T max = get(0);

        for (int i = 1; i < size(); i++) {
            T curr = get(i);
            if (comp.compare(curr, max) > 0) {
                max = curr;
            }
        }

        return max;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) return null;

        T max = get(0);

        for (int i = 1; i < size(); i++) {
            T curr = get(i);
            if (c.compare(curr, max) > 0) {
                max = curr;
            }
        }

        return max;
    }
}
