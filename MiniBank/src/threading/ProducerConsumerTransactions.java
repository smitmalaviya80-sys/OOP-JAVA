
package threading;

public class ProducerConsumerTransactions {

    static class Buffer {
        private final int[] transactions = new int[3];
        private int count = 0;
        private int in = 0;
        private int out = 0;

        public synchronized void put(int transaction)
                throws InterruptedException {
            while (count == transactions.length) {
                wait();
            }

            transactions[in] = transaction;
            in = (in + 1) % transactions.length;
            count++;

            System.out.println("Produced: " + transaction);
            notify();
        }

        public synchronized int get() throws InterruptedException {
            while (count == 0) {
                wait();
            }

            int transaction = transactions[out];
            out = (out + 1) % transactions.length;
            count--;

            System.out.println("Consumed: " + transaction);
            notify();
            return transaction;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.put(i * 100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.get();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All transactions processed.");
    }
}
