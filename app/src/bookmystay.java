import java.util.*;

// Reservation class
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

// Booking History (stores confirmed bookings)
class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        history.add(reservation); // maintains insertion order
    }

    public List<Reservation> getAllReservations() {
        return history;
    }
}

// Reporting Service
class BookingReportService {

    public void generateReport(List<Reservation> reservations) {

        System.out.println("Booking History Report");

        for (Reservation r : reservations) {
            System.out.println("Guest: " + r.getGuestName()
                    + ", Room Type: " + r.getRoomType());
        }
    }
}

// Main Class
public class bookmystay {

    public static void main(String[] args) {

        BookingHistory bookingHistory = new BookingHistory();

        // Simulate confirmed bookings
        bookingHistory.addReservation(new Reservation("Abhi", "Single"));
        bookingHistory.addReservation(new Reservation("Subha", "Double"));
        bookingHistory.addReservation(new Reservation("Vanmathi", "Suite"));

        // Generate report
        BookingReportService reportService = new BookingReportService();

        System.out.println("Booking History and Reporting");

        reportService.generateReport(bookingHistory.getAllReservations());
    }
}