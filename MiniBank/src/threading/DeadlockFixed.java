
package threading;

public class DeadlockFixed {

    static class Account {
        final int accountNumber;
        int balance = 1000;

        Account(int accountNumber) {
            this.accountNumber = accountNumber;
        }
    }

    static void transfer(Account from, Account to, int amount) {
        Account first = from.accountNumber < to.accountNumber ? from : to;
        Account second = from.accountNumber < to.accountNumber ? to : from;

        synchronized (first) {
            synchronized (second) {
                if (from.balance >= amount) {
                    from.balance -= amount;
                    to.balance += amount;

                    System.out.println(Thread.currentThread().getName()
                            + " transferred " + amount
                            + " from " + from.accountNumber
                            + " to " + to.accountNumber);
                } else {
                    System.out.println("Insufficient funds");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Account a = new Account(1);
        Account b = new Account(2);

        Thread t1 = new Thread(() -> transfer(a, b, 100), "Thread-A");
        Thread t2 = new Thread(() -> transfer(b, a, 200), "Thread-B");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Account A balance: " + a.balance);
        System.out.println("Account B balance: " + b.balance);
    }
}
