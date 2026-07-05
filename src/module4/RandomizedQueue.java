import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int count;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        count = 0;
    }

    private void resize(int capacity) {
        Item[] newItems = (Item[]) new Object[capacity];
        for (int i = 0; i < count; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return count;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add a null item into RandomizedQueue");
        }
        if (count == items.length) {
            resize(2 * items.length);
        }
        items[count++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue is empty");
        }
        int randomIndex = StdRandom.uniformInt(count);
        Item item = items[randomIndex];
        items[randomIndex] = items[count - 1]; // Move the last item to the random index
        items[count - 1] = null; // Avoid loitering
        count--;
        if (count > 0 && count <= items.length / 4) {
            resize(items.length / 2);
        }
        
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("RandomizedQueue is empty");
        }
        int randomIndex = StdRandom.uniformInt(count);
        return items[randomIndex];
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] shuffledItems;
        private int current;

        public RandomizedQueueIterator() {
            shuffledItems = (Item[]) new Object[count];
            for (int i = 0; i < count; i++) {
                shuffledItems[i] = items[i];
            }
            StdRandom.shuffle(shuffledItems);
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current < shuffledItems.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the iterator");
            }
            return shuffledItems[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        for (int i = 0; i < 10; i++) {
            randomizedQueue.enqueue(i);
        }
        StdOut.println("RandomizedQueue size: " + randomizedQueue.size());
        StdOut.println("RandomizedQueue sample: " + randomizedQueue.sample());
        StdOut.println("RandomizedQueue items in random order:");
        for (int item : randomizedQueue) {
            StdOut.print(item + " ");
        }
        StdOut.println();
        StdOut.println("Dequeueing all items:");
        while (!randomizedQueue.isEmpty()) {
            StdOut.print(randomizedQueue.dequeue() + " ");
        }
        StdOut.println();
        StdOut.println("Final size: " + randomizedQueue.size());
    }

}