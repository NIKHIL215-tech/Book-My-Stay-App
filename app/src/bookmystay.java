import java.util.*;

/**
 * CLASS - Reservation
 */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
}

/**
 * CLASS - BookingRequestQueue
 */
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

/**
 * CLASS - RoomInventory
 */
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void decrement(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

/**
 * CLASS - BookingService
 */
class BookingService {

    private RoomInventory inventory;
    private Map<String, Set<String>> allocatedRooms = new HashMap<>();
    private Map<String, Integer> counters = new HashMap<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processBookings(BookingRequestQueue queue) {
        System.out.println("Room Allocation Processing");

        while (!queue.isEmpty()) {
            Reservation r = queue.getNextRequest();
            String type = r.getRoomType();

            if (inventory.getAvailability(type) > 0) {

                // Generate unique room ID
                int count = counters.getOrDefault(type, 0) + 1;
                counters.put(type, count);
                String roomId = type + "-" + count;

                // Ensure uniqueness using Set
                allocatedRooms.putIfAbsent(type, new HashSet<>());
                allocatedRooms.get(type).add(roomId);

                // Update inventory immediately
                inventory.decrement(type);

                // Confirm booking
                System.out.println("Booking confirmed for Guest: "
                        + r.getGuestName() + ", Room ID: " + roomId);

            } else {
                System.out.println("No rooms available for Guest: "
                        + r.getGuestName() + ", Room Type: " + type);
            }
        }
    }
}

/**
 * MAIN CLASS - UseCase6RoomAllocationService
 */
public class bookmystay {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();

        // Booking requests
        queue.addRequest(new Reservation("Abhi", "Single"));
        queue.addRequest(new Reservation("Subha", "Single"));
        queue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Process bookings
        BookingService service = new BookingService(inventory);
        service.processBookings(queue);
    }
}