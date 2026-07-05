import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        // takes in integer k as a command-line argument
        if (args.length < 1) {
            throw new IllegalArgumentException("Please provide an integer k as a command-line argument.");
        }
        int k = Integer.parseInt(args[0]);
        if (k < 0) {
            throw new IllegalArgumentException("k must be a non-negative integer.");
        }

        int count = 0;

        RandomizedQueue<String> queue = new RandomizedQueue<>();
        // Add strings to the queue
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            count++;

            if (count <= k) {
                queue.enqueue(item);
            } else if (StdRandom.bernoulli((double) k / count)) {
                queue.dequeue(); // Remove a random item from the queue
                queue.enqueue(item); // Add the new item to the queue
            }
        }

       // Print k random strings from the queue
       for (int i = 0; i < k; i++) {
           StdOut.println(queue.dequeue());
       }
   }
}