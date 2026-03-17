import java.util.HashMap;
import java.util.Map;

/**
 * ABSTRACT CLASS - Room
 */
abstract class Room {
    private String type;
    private int beds;
    private int size;
    private double price;

    public Room(String type, int beds, int size, double price) {
        this.type = type;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public String getType() { return type; }
    public int getBeds() { return beds; }
    public int getSize() { return size; }
    public double getPrice() { return price; }

    public abstract void displayDetails(int available);
}

/**
 * Single Room
 */
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 250, 1500.0);
    }

    @Override
    public void displayDetails(int available) {
        System.out.println("Single Room:");
        System.out.println("Beds: " + getBeds());
        System.out.println("Size: " + getSize() + " sqft");
        System.out.println("Price per night: " + getPrice());
        System.out.println("Available Rooms: " + available);
        System.out.println();
    }
}

/**
 * Double Room
 */
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 400, 2500.0);
    }

    @Override
    public void displayDetails(int available) {
        System.out.println("Double Room:");
        System.out.println("Beds: " + getBeds());
        System.out.println("Size: " + getSize() + " sqft");
        System.out.println("Price per night: " + getPrice());
        System.out.println("Available Rooms: " + available);
        System.out.println();
    }
}

/**
 * Suite Room
 */
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 750, 5000.0);
    }

    @Override
    public void displayDetails(int available) {
        System.out.println("Suite Room:");
        System.out.println("Beds: " + getBeds());
        System.out.println("Size: " + getSize() + " sqft");
        System.out.println("Price per night: " + getPrice());
        System.out.println("Available Rooms: " + available);
        System.out.println();
    }
}

/**
 * RoomInventory (Read-Only Access for Search)
 */
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

/**
 * Search Service (Read-only)
 */
class RoomSearchService {

    public void searchAvailableRooms(RoomInventory inventory, Room[] rooms) {
        System.out.println("Available Rooms:\n");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getType());

            // Filter only available rooms
            if (available > 0) {
                room.displayDetails(available);
            }
        }
    }
}

/**
 * MAIN CLASS - UseCase4RoomSearch
 */
public class bookmystay {

    public static void main(String[] args) {

        System.out.println("=== BookMyStay - Room Search v4.0 ===\n");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Room objects
        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        // Search service
        RoomSearchService searchService = new RoomSearchService();

        // Perform search (read-only)
        searchService.searchAvailableRooms(inventory, rooms);
    }
}