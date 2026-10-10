import java.util.HashMap;
import java.util.Map;

class OutOfStockException extends Exception {
    private int shortfall;

    public OutOfStockException(String message, int shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {

    private Map<String, Integer> stock = new HashMap<>();

    public void addStock(String item, int quantity) {
        stock.put(item, quantity);
    }

    public void issue(String item, int qty)
            throws OutOfStockException, InvalidQuantityException {

        if (qty <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than zero."
            );
        }

        int available = stock.getOrDefault(item, 0);

        if (qty > available) {
            int shortfall = qty - available;

            throw new OutOfStockException(
                    "Not enough stock for " + item,
                    shortfall
            );
        }

        stock.put(item, available - qty);

        System.out.println(
                "Issued " + qty + " " + item +
                ". Remaining: " + (available - qty)
        );
    }
}

public class StockIssue {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        warehouse.addStock("Laptop", 10);
        warehouse.addStock("Mouse", 5);
        warehouse.addStock("Keyboard", 3);

        String[] items = {
                "Laptop",
                "Mouse",
                "Keyboard",
                "Laptop"
        };

        int[] quantities = {
                4,
                8,
                -2,
                10
        };

        for (int i = 0; i < items.length; i++) {

            try {
                warehouse.issue(items[i], quantities[i]);

            } catch (OutOfStockException e) {
                System.out.println(
                        "Out of stock: " + e.getMessage()
                );
                System.out.println(
                        "Shortfall: " + e.getShortfall()
                );

            } catch (InvalidQuantityException e) {
                System.out.println(
                        "Invalid quantity: " + e.getMessage()
                );
            }

            System.out.println();
        }

        System.out.println("All requests processed.");
    }
}