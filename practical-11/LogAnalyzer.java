
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.stream.Stream;

public class LogAnalyzer {

    public static void main(String[] args) {

        Path directory = Path.of("logs");

        try {
            Files.createDirectories(directory);

            Files.write(directory.resolve("app1.txt"), List.of(
                "INFO: Application started",
                "ERROR: Database connection failed",
                "INFO: Retrying connection"
            ));

            Files.write(directory.resolve("app2.txt"), List.of(
                "INFO: User logged in",
                "ERROR: Invalid password",
                "ERROR: Request failed",
                "INFO: Application closed"
            ));

            Files.write(directory.resolve("app3.txt"), List.of(
                "INFO: Server started",
                "INFO: Request successful"
            ));

            int totalLines = 0;
            int keywordLines = 0;
            String keyword = "ERROR";

            System.out.println("LOG FILE ANALYSIS");
            System.out.println("--------------------");

            try (Stream<Path> files = Files.list(directory)) {

                for (Path file : files.filter(Files::isRegularFile).toList()) {

                    BasicFileAttributes attributes =
                            Files.readAttributes(
                                file, BasicFileAttributes.class);

                    System.out.println("\nFile: " + file.getFileName());
                    System.out.println(
                            "Size: " + attributes.size() + " bytes");

                    try (Stream<String> lines = Files.lines(file)) {

                        for (String line : lines.toList()) {
                            totalLines++;

                            if (line.contains(keyword)) {
                                keywordLines++;
                            }
                        }
                    }
                }
            }

            System.out.println("\n--------------------");
            System.out.println("Total lines: " + totalLines);
            System.out.println(
                    "Lines containing " + keyword + ": " + keywordLines);

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
