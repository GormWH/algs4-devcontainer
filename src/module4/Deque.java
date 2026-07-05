import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Node prev;
        private Item item;
        private Node next;

        public Node(Item item) {
            this.item = item;
            prev = null;
            next = null;
        }
    }

    private int count;
    private Node first;
    private Node last;

    // construct an empty deque
    public Deque() {
        count = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item into Deque");
        }
        Node newNode = new Node(item);
        
        Node oldFirst = first;

        first = newNode;
        
        if (count == 0) {
            last = newNode;
        } else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }

        count++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item into Deque");
        }
        Node newNode = new Node(item);
        
        Node oldLast = last;

        last = newNode;
        
        if (count == 0) {
            first = newNode;
        } else {
            last.prev = oldLast;
            oldLast.next = last;
        }
        count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Node oldFirst = first;

        first = oldFirst.next;
        if (first == null) {
            last = null;
        } else {
            first.prev = null;
        }

        count--;

        return oldFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        Node oldLast = last;

        last = oldLast.prev;
        if (last == null) {
            first = null;
        } else {
            last.next = null;
        }

        count--;

        return oldLast.item;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the iterator");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("A");
        deque.addLast("B");
        deque.addFirst("C");
        StdOut.println("Size: " + deque.size()); // Size: 3
        StdOut.println("Remove First: " + deque.removeFirst()); // Remove First: C
        StdOut.println("Remove Last: " + deque.removeLast()); // Remove Last: B
        StdOut.println("Size: " + deque.size()); // Size: 1
        StdOut.println("Iterator:");
        for (String item : deque) {
            StdOut.println(item);
        }
    }

}