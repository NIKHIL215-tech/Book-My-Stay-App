import java.util.*;

// Booking Request
class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Inventory Manager (Thread-Safe)
class InventoryManager {

    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Integer> roomCounter = new HashMap<>();

    public InventoryManager() {
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        roomCounter.put("Single", 0);
        roomCounter.put("Double", 0);
        roomCounter.put("Suite", 0);
    }

    // Critical Section
    public synchronized String allocateRoom(String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available <= 0) {
            return null;
        }

        // decrement inventory
        inventory.put(roomType, available - 1);

        // generate room ID
        int count = roomCounter.get(roomType) + 1;
        roomCounter.put(roomType, count);

        return roomType + "-" + count;
    }

    public void printInventory() {
        System.out.println("Remaining Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}

// Booking Processor (Thread)
class BookingProcessor extends Thread {

    private BookingRequest request;
    private InventoryManager inventoryManager;

    public BookingProcessor(BookingRequest request, InventoryManager manager) {
        this.request = request;
        this.inventoryManager = manager;
    }

    @Override
    public void run() {

        String roomId = inventoryManager.allocateRoom(request.roomType);

        if (roomId != null) {
            System.out.println("Booking confirmed for Guest: "
                    + request.guestName + ", Room ID: " + roomId);
        } else {
            System.out.println("Booking failed for Guest: "
                    + request.guestName + " (No rooms available)");
        }
    }
}

// Main Class
public class bookmystay {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation");

        InventoryManager manager = new InventoryManager();

        // Simulated concurrent requests
        List<BookingProcessor> threads = Arrays.asList(
                new BookingProcessor(new BookingRequest("Abhi", "Single"), manager),
                new BookingProcessor(new BookingRequest("Vanmathi", "Double"), manager),
                new BookingProcessor(new BookingRequest("Kural", "Suite"), manager),
                new BookingProcessor(new BookingRequest("Subha", "Single"), manager)
        );

        // Start threads
        for (Thread t : threads) {
            t.start();
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Final inventory
        manager.printInventory();
    }
}