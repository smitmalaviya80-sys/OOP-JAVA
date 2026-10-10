
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunner {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            final int taskId = i;

            executor.submit(() -> {
                System.out.println("Task " + taskId + " running on "
                        + Thread.currentThread().getName());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();

        if (executor.awaitTermination(30, TimeUnit.SECONDS)) {
            System.out.println("All tasks completed.");
        } else {
            System.out.println("Tasks did not finish in time.");
            executor.shutdownNow();
        }
    }
}
