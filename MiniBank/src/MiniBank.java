import model.Account;
import model.SavingsAccount;
import service.AccountWorker;

public class MiniBank {

    public static void main(String[] args) throws InterruptedException {

        Account account = new SavingsAccount(
                "SA001",
                "Smit",
                0,
                1000
        );

        int numberOfThreads = 10;
        int times = 10000;
        long amount = 10;

        Thread[] threads = new Thread[numberOfThreads];

        System.out.println("=== MiniBank Thread Test ===");

        for (int i = 0; i < numberOfThreads; i++) {

            threads[i] = new Thread(
                    new AccountWorker(account, times, amount),
                    "Worker-" + (i + 1)
            );

            System.out.println(
                    threads[i].getName() + " created"
            );

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long expected = (long) numberOfThreads * times * amount;

        System.out.println();
        System.out.println("Expected balance: " + expected);
        System.out.println("Actual balance: " + account.balance());
    }
}