
import java.nio.file.Path;
import model.Account;
import model.SavingsAccount;
import persistence.StatePersister;
import persistence.TransactionLog;
import persistence.ReportGenerator;

public class PersistenceDemo {

    public static void main(String[] args) {
        Path accountFile = Path.of("accounts.dat");
        Path logsFolder = Path.of("logs");
        Path transactionFile = logsFolder.resolve("transactions.txt");
        Path reportFile = Path.of("end-of-day-report.txt");

        try {
            Account[] accounts = {
                new SavingsAccount("SA001", "Smit", 1000, 500),
                new SavingsAccount("SA002", "Rahul", 2000, 500)
            };

            StatePersister.save(accounts, accountFile);
            System.out.println("Accounts saved successfully.");

            Account[] loadedAccounts =
                    StatePersister.load(accountFile);

            System.out.println("\nReloaded accounts:");

            for (Account account : loadedAccounts) {
                System.out.println(account);
            }

            TransactionLog.append(transactionFile, "DEPOSIT,500");
            TransactionLog.append(transactionFile, "WITHDRAW,100");
            TransactionLog.append(transactionFile, "DEPOSIT,250");
            TransactionLog.append(transactionFile, "WITHDRAW,50");

            System.out.println("\nTransactions logged successfully.");

            ReportGenerator.generate(logsFolder, reportFile);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
