class BookingSystem {
    private int seatsLeft = 5;
    private int successfulBookings = 0;

    public void book() {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            seatsLeft--;
            successfulBookings++;
        }
    }

    public synchronized void synchronizedBook() {
        if (seatsLeft > 0) {
            seatsLeft--;
            successfulBookings++;
        }
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }

    public int getSuccessfulBookings() {
        return successfulBookings;
    }
}

public class SeatBookingRace {

    public static void main(String[] args) throws InterruptedException {

        BookingSystem system1 = new BookingSystem();
        Thread[] threads1 = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads1[i] = new Thread(() -> {
                system1.book();
            });

            threads1[i].start();
        }

        for (Thread thread : threads1) {
            thread.join();
        }

        System.out.println("Without synchronization:");
        System.out.println("Successful bookings: "
                + system1.getSuccessfulBookings());
        System.out.println("Seats left: "
                + system1.getSeatsLeft());

        BookingSystem system2 = new BookingSystem();
        Thread[] threads2 = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads2[i] = new Thread(() -> {
                system2.synchronizedBook();
            });

            threads2[i].start();
        }

        for (Thread thread : threads2) {
            thread.join();
        }

        System.out.println();
        System.out.println("With synchronization:");
        System.out.println("Successful bookings: "
                + system2.getSuccessfulBookings());
        System.out.println("Seats left: "
                + system2.getSeatsLeft());
    }
}