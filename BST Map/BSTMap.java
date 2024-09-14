package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V extends Comparable<V>> implements Map61B<K, V> {
    private class BSTNode {
        private K key;
        private V val;

        private BSTNode left, right;

        public BSTNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private BSTNode root;
    private int size;

    public void clear() {
        size = 0;
        root = null;
    }

    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        return containsKey(key, root);
    }

    public boolean containsKey(K key, BSTNode node) {
        if (node == null) {
            return false;
        }

        int cmp = key.compareTo(node.key);

        if (cmp > 0) {
            return containsKey(key, node.right);
        } else if (cmp < 0) {
            return containsKey(key, node.left);
        } else {
            return true;
        }
    }

    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, BSTNode node) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return get(key, node.left);
        } else if (cmp > 0) {
            return get(key, node.right);
        } else {
            return node.val;
        }
    }

    public int size() {
        return size;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }

        root = put(key, value, root);
    }

    private BSTNode put(K key, V value, BSTNode node) {
        if (node == null) {
            size++;
            return new BSTNode(key, value);
        }

        if (key.compareTo(node.key) > 0) {
            node.right = put(key, value, node.right);
        }
        else if (key.compareTo(node.key) < 0) {
            node.left = put(key, value, node.left);
        }
        else {
            node.val = value;
        }

        return node;
    }

    public Set<K> keySet() {
        throw new UnsupportedOperationException("");
    }

    public V remove(K key) {
        throw new UnsupportedOperationException("");
    }

    public V remove(K key, V value) {
        throw new UnsupportedOperationException("");
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("");
    }
}
