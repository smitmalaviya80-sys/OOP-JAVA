
package threading;

public class TransactionDemo {

    static class Account {
        private int balance = 1000;

        public synchronized void deposit(int amount) {
            balance += amount;
            System.out.println(Thread.currentThread().getName()
                    + " deposited " + amount
                    + " | Balance: " + balance);
        }

        public synchronized void withdraw(int amount) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println(Thread.currentThread().getName()
                        + " withdrew " + amount
                        + " | Balance: " + balance);
            } else {
                System.out.println("Insufficient balance");
            }
        }

        public synchronized int getBalance() {
            return balance;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TransactionProcessor processor = new TransactionProcessor();
        Account account = new Account();

        for (int i = 1; i <= 10; i++) {
            final int id = i;

            processor.submit(() -> {
                if (id % 2 == 0) {
                    account.deposit(100);
                } else {
                    account.withdraw(50);
                }
            });
        }

        processor.stop();

        System.out.println("Final balance: " + account.getBalance());
    }
}
