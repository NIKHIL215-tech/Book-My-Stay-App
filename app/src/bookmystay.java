import java.util.LinkedList;
import java.util.Queue;

/**
 * CLASS - Reservation
 *
 * Represents a booking request made by a guest.
 */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

/**
 * CLASS - BookingRequestQueue
 *
 * Manages booking requests using FIFO principle.
 */
class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    // Add request to queue
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
    }

    // Process requests in FIFO order (no inventory update)
    public void processRequests() {
        System.out.println("Booking Request Queue");

        while (!queue.isEmpty()) {
            Reservation r = queue.poll();
            System.out.println("Processing booking for Guest: "
                    + r.getGuestName() + ", Room Type: "
                    + r.getRoomType());
        }
    }
}

/**
 * MAIN CLASS - UseCase5BookingRequestQueue
 */
public class bookmystay {

    public static void main(String[] args) {

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Incoming booking requests (FIFO order)
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Subha", "Double"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Suite"));

        // Process requests
        bookingQueue.processRequests();
    }
}