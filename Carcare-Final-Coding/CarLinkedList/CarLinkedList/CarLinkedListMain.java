package CarLinkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CarLinkedList carLinkedList = new CarLinkedList();
        loadCarsFromCSV(
                "CarLinkedList\\CarLinkedList\\car_care_management_system_dataset.csv",
                carLinkedList);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();
            System.out.println("\n=============================");
            System.out.println("Car Service Management System");
            System.out.println("=============================");
            System.out.println("Menu:");
            System.out.println("1. Traversal");
            System.out.println("2. Insertion");
            System.out.println("3. Deletion");
            System.out.println("4. Search");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    carLinkedList.traverse();
                    break;
                case 2:
                    insertCar(scanner, carLinkedList);
                    break;
                case 3:
                    deleteCar(scanner, carLinkedList);
                    break;
                case 4:
                    searchCarByServiceId(scanner, carLinkedList);
                    break;
                case 5:
                    writeCarsToCSV(
                            "CarLinkedList\\CarLinkedList\\car_care_management_system_dataset.csv",
                            carLinkedList);
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            scanner.nextLine(); // Consume the newline left-over
            System.out.println("Press Enter to continue...");
            scanner.nextLine(); // Wait for the user to press Enter
        }
    }

    public static void loadCarsFromCSV(String filePath, CarLinkedList list) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int serviceId = Integer.parseInt(values[0].trim());
                String make = values[1].trim();
                int year = Integer.parseInt(values[2].trim());
                String serviceType = values[3].trim();
                double totalCost = Double.parseDouble(values[4].trim());
                list.insert(new Car(serviceId, make, year, serviceType, totalCost));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertCar(Scanner scanner, CarLinkedList carLinkedList) {
        System.out.print("Enter Service ID: ");
        int serviceId = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline
        System.out.print("Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline
        System.out.print("Enter Service Type: ");
        String serviceType = scanner.nextLine();
        System.out.print("Enter Total Cost: ");
        double totalCost = scanner.nextDouble();
        scanner.nextLine(); // consume the remaining newline

        carLinkedList.insert(new Car(serviceId, make, year, serviceType, totalCost));
        System.out.println("Car inserted successfully.");
    }

    public static void deleteCar(Scanner scanner, CarLinkedList carLinkedList) {
        System.out.print("Enter Service ID to delete: ");
        int serviceId = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline

        carLinkedList.deleteByServiceId(serviceId);
        System.out.println("Car deleted successfully.");
    }

    public static void searchCarByServiceId(Scanner scanner, CarLinkedList carLinkedList) {
        System.out.print("Enter Service ID to search: ");
        int serviceId = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline

        Node current = carLinkedList.head;
        boolean found = false;

        while (current != null) {
            Car car = current.car;
            if (car.getServiceId() == serviceId) {
                // Car with the specified Service ID found
                found = true;
                System.out.println("Car found:");
                System.out.println("Service ID: " + car.getServiceId());
                System.out.println("Make: " + car.getMake());
                System.out.println("Year: " + car.getYear());
                System.out.println("Service Type: " + car.getServiceType());
                System.out.println("Total Cost: " + car.getTotalCost());
                System.out.println(); // Add a newline for separation
                break; // Exit the loop once found
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("Car with Service ID " + serviceId + " not found.");
        }

    }

    public static void writeCarsToCSV(String filePath, CarLinkedList carLinkedList) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ServiceId,Make,Year,ServiceType,TotalCost\n");
            Node current = carLinkedList.head;
            while (current != null) {
                Car car = current.car;
                // Replace direct field access with getter method calls
                writer.write(car.getServiceId() + "," + car.getMake() + "," + car.getYear() + "," + car.getServiceType()
                        + "," + car.getTotalCost() + "\n");
                current = current.next;
            }
            System.out.println("Data written to CSV successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}