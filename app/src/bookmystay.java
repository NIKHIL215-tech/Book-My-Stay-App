import java.util.*;

// Reservation class
class Reservation {
    private String reservationId;
    private String roomType;
    private boolean isCancelled;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.isCancelled = false;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancel() {
        isCancelled = true;
    }
}

// Inventory Manager
class InventoryManager {
    private Map<String, Integer> inventory = new HashMap<>();

    public InventoryManager() {
        inventory.put("Single", 5); // initial count before rollback
    }

    public void increment(String roomType) {
        inventory.put(roomType, inventory.getOrDefault(roomType, 0) + 1);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

// Cancellation Service
class CancellationService {

    private Map<String, Reservation> reservations;
    private Stack<String> rollbackStack;
    private InventoryManager inventoryManager;

    public CancellationService(Map<String, Reservation> reservations,
                               InventoryManager inventoryManager) {
        this.reservations = reservations;
        this.inventoryManager = inventoryManager;
        this.rollbackStack = new Stack<>();
    }

    public void cancelBooking(String reservationId) {

        // Validation
        if (!reservations.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation does not exist.");
            return;
        }

        Reservation res = reservations.get(reservationId);

        if (res.isCancelled()) {
            System.out.println("Cancellation failed: Already cancelled.");
            return;
        }

        // Rollback process
        rollbackStack.push(reservationId); // track rollback
        inventoryManager.increment(res.getRoomType()); // restore inventory
        res.cancel(); // mark cancelled

        // Output
        System.out.println("Booking cancelled successfully. Inventory restored for room type: "
                + res.getRoomType());

        System.out.println("Rollback History (Most Recent First):");

        while (!rollbackStack.isEmpty()) {
            System.out.println("Released Reservation ID: " + rollbackStack.pop());
        }

        System.out.println("Updated " + res.getRoomType()
                + " Room Availability: "
                + inventoryManager.getAvailability(res.getRoomType()));
    }
}

// Main Class
public class bookmystay {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        // Setup existing reservation
        Map<String, Reservation> reservations = new HashMap<>();
        reservations.put("Single-1", new Reservation("Single-1", "Single"));

        InventoryManager inventoryManager = new InventoryManager();

        CancellationService service =
                new CancellationService(reservations, inventoryManager);

        // Perform cancellation
        service.cancelBooking("Single-1");
    }
}