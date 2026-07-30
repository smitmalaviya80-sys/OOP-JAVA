public class CinemaShow {
    private String title;
    private int seatsAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seatsAvailable = capacity;
    }

    CinemaShow(String title) {
        this(title, 100);
    }

    public boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        } else {
            System.out.println("unchanged");
            return false;
        }
    }

    public void cancel(int n) {
        seatsAvailable += n;
        if (seatsAvailable > capacity) {
            seatsAvailable = capacity;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    // static keyword used so we don't need to create an object to call this directly
    public static int getTotalBooked() {
        return totalBooked;
    }

    public static void main(String[] args) {
        CinemaShow c1 = new CinemaShow("spiderman", 50);
        CinemaShow c2 = new CinemaShow("Matrix");

        System.out.println("booking and canceling for show-1 movie: spiderman");

        boolean result;

        result = c1.book(30);
        System.out.println("booked successfully " + result + " | seats available: " + c1.getSeatsAvailable());

        result = c1.book(30);
        System.out.println("booked successfully " + result + " | seats available: " + c1.getSeatsAvailable());

        System.out.println("canceling seats");

        c1.cancel(20);
        System.out.println("cancel done | available seats: " + c1.getSeatsAvailable());

        c1.cancel(50);
        System.out.println("cancel done | available seats: " + c1.getSeatsAvailable());

        System.out.println("\nTotal booked across all shows: " + CinemaShow.getTotalBooked());
    }
}