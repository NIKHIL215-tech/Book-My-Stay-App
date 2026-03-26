import java.io.*;
import java.util.*;

// Inventory Manager (Serializable)
class InventoryManager implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Integer> inventory;

    public InventoryManager() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "inventory.dat";

    // Save state
    public static void save(InventoryManager manager) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            oos.writeObject(manager);
            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    // Load state
    public static InventoryManager load() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return new InventoryManager();
        }

        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            return (InventoryManager) ois.readObject();

        } catch (Exception e) {
            System.out.println("Corrupted data detected. Starting fresh.");
            return new InventoryManager();
        }
    }
}

// Main Class
public class bookmystay {

    public static void main(String[] args) {

        System.out.println("System Recovery");

        // Load previous state (if exists)
        InventoryManager manager = PersistenceService.load();

        // Display current state
        manager.displayInventory();

        // Save state before shutdown
        PersistenceService.save(manager);
    }
}