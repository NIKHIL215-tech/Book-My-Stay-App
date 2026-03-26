import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Validator Class
class BookingValidator {

    private static final Set<String> VALID_ROOM_TYPES =
            new HashSet<>(Arrays.asList("Single", "Double", "Suite"));

    public static void validate(String guestName, String roomType)
            throws InvalidBookingException {

        // Validate guest name
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Validate room type (case-sensitive as per requirement)
        if (!VALID_ROOM_TYPES.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}

// Main Class
public class bookmystay {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Booking Validation");

        try {
            System.out.print("Enter guest name: ");
            String guestName = sc.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = sc.nextLine();

            // Validate input
            BookingValidator.validate(guestName, roomType);

            // If validation passes
            System.out.println("Booking successful for " + guestName);

        } catch (InvalidBookingException e) {

            // Graceful failure handling
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}