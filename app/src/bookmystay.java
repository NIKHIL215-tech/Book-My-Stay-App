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
    }
}

/**
 * RoomInventory using HashMap
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
 * MAIN CLASS - UseCase3InventorySetup
 */
public class bookmystay {

    public static void main(String[] args) {

        System.out.println("Hotel Room Inventory Status");

        RoomInventory inventory = new RoomInventory();

        Room single = new SingleRoom();
        Room doub = new DoubleRoom();
        Room suite = new SuiteRoom();

        single.displayDetails(inventory.getAvailability("Single Room"));
        doub.displayDetails(inventory.getAvailability("Double Room"));
        suite.displayDetails(inventory.getAvailability("Suite Room"));
    }
}
