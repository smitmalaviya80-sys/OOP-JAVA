class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public synchronized void synchronizedIncrement() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class CounterRace {

    static final int THREADS = 10;
    static final int INCREMENTS = 100000;

    public static void main(String[] args) throws InterruptedException {

        Counter counter1 = new Counter();
        Thread[] threads1 = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            threads1[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++) {
                    counter1.increment();
                }
            });

            threads1[i].start();
        }

        for (Thread thread : threads1) {
            thread.join();
        }

        System.out.println("Without synchronization:");
        System.out.println("Expected: " + (THREADS * INCREMENTS));
        System.out.println("Actual: " + counter1.getCount());

        Counter counter2 = new Counter();
        Thread[] threads2 = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            threads2[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++) {
                    counter2.synchronizedIncrement();
                }
            });

            threads2[i].start();
        }

        for (Thread thread : threads2) {
            thread.join();
        }

        System.out.println();
        System.out.println("With synchronization:");
        System.out.println("Expected: " + (THREADS * INCREMENTS));
        System.out.println("Actual: " + counter2.getCount());
    }
}