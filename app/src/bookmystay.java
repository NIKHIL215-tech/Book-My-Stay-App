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

    public String getType() {
        return type;
    }

    public int getBeds() {
        return beds;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayDetails();
}

/**
 * Single Room Class
 */
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 250, 1500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Single Room:");
        System.out.println("Beds: " + getBeds());
        System.out.println("Size: " + getSize() + " sqft");
        System.out.println("Price per night: " + getPrice());
    }
}

/**
 * Double Room Class
 */
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 400, 2500.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Double Room:");
        System.out.println("Beds: " + getBeds());
        System.out.println("Size: " + getSize() + " sqft");
        System.out.println("Price per night: " + getPrice());
    }
}

/**
 * Suite Room Class
 */
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 750, 5000.0);
    }

    @Override
    public void displayDetails() {
        System.out.println("Suite Room:");
        System.out.println("Beds: " + getBeds());
        System.out.println("Size: " + getSize() + " sqft");
        System.out.println("Price per night: " + getPrice());
    }
}

/**
 * MAIN CLASS - UseCase2RoomInitialization
 */
public class bookmystay{

    public static void main(String[] args) {

        System.out.println("Hotel Room Initialization");

        Room single = new SingleRoom();
        Room doub = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        single.displayDetails();
        System.out.println("Available: " + singleAvailable);

        doub.displayDetails();
        System.out.println("Available: " + doubleAvailable);

        suite.displayDetails();
        System.out.println("Available: " + suiteAvailable);
    }
}