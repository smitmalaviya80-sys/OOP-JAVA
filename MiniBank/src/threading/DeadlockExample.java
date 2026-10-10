
package threading;

public class DeadlockExample {

    static class Account {
        final int accountNumber;
        int balance = 1000;

        Account(int accountNumber) {
            this.accountNumber = accountNumber;
        }
    }

    static void transfer(Account from, Account to, int amount) {
        synchronized (from) {
            System.out.println(Thread.currentThread().getName()
                    + " locked account " + from.accountNumber);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            synchronized (to) {
                from.balance -= amount;
                to.balance += amount;
                System.out.println("Transfer completed");
            }
        }
    }

    public static void main(String[] args) {
        Account a = new Account(1);
        Account b = new Account(2);

        Thread t1 = new Thread(() -> transfer(a, b, 100), "Thread-A");
        Thread t2 = new Thread(() -> transfer(b, a, 200), "Thread-B");

        t1.start();
        t2.start();
    }
}
