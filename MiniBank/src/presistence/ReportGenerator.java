
package persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

public class ReportGenerator {

    public static void generate(Path logsFolder, Path reportFile)
            throws IOException {

        long totalDeposits = 0L;
        long totalWithdrawals = 0L;

        StringBuilder report = new StringBuilder();

        report.append("MINIBANK END-OF-DAY REPORT\n");
        report.append("=========================\n\n");

        if (Files.isDirectory(logsFolder)) {
            try (DirectoryStream<Path> stream =
                    Files.newDirectoryStream(logsFolder)) {

                for (Path file : stream) {
                    if (!Files.isRegularFile(file)) {
                        continue;
                    }

                    BasicFileAttributes attributes =
                            Files.readAttributes(
                                    file, BasicFileAttributes.class);

                    report.append("File: ")
                          .append(file.getFileName()).append("\n");
                    report.append("Size: ")
                          .append(attributes.size()).append(" bytes\n");
                    report.append("Last modified: ")
                          .append(Instant.ofEpochMilli(
                                  attributes.lastModifiedTime().toMillis()))
                          .append("\n");

                    try (BufferedReader reader = Files.newBufferedReader(
                            file, StandardCharsets.UTF_8)) {

                        String line;

                        while ((line = reader.readLine()) != null) {
                            String[] parts = line.trim().split(",", 2);

                            if (parts.length != 2) {
                                continue;
                            }

                            try {
                                long amount = Long.parseLong(
                                        parts[1].trim());

                                if (amount < 0) {
                                    continue;
                                }

                                if (parts[0].trim().equalsIgnoreCase(
                                        "DEPOSIT")) {
                                    totalDeposits += amount;
                                } else if (parts[0].trim().equalsIgnoreCase(
                                        "WITHDRAW")) {
                                    totalWithdrawals += amount;
                                }
                            } catch (NumberFormatException e) {
                                report.append("Invalid transaction: ")
                                      .append(line).append("\n");
                            }
                        }
                    }

                    report.append("\n");
                }
            }
        } else {
            report.append("Log folder does not exist or is not a directory.\n\n");
        }

        report.append("TOTAL DEPOSITS: ").append(totalDeposits).append("\n");
        report.append("TOTAL WITHDRAWALS: ")
              .append(totalWithdrawals).append("\n");
        report.append("NET TRANSACTION AMOUNT: ")
              .append(totalDeposits - totalWithdrawals).append("\n");

        Path parent = reportFile.toAbsolutePath().getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                reportFile,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE)) {

            writer.write(report.toString());
        }

        System.out.println(report);
        System.out.println("Report saved to: " + reportFile);
    }
}
