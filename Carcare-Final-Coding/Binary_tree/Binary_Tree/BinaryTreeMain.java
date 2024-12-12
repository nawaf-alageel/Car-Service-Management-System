package Binary_Tree;

import java.io.*;
import java.util.Scanner;

public class BinaryTreeMain {
    // Create a BinaryTree instance to store service data
    private static BinaryTree serviceTree = new BinaryTree();
    // Define the file path for the CSV data
    private static final String FILE_PATH = "Binary_tree\\Binary_Tree\\car_care_management_system_dataset.csv";

    public static void main(String[] args) {
        // Load initial data from the CSV file
        loadCSVData(FILE_PATH);

        try (Scanner scanner = new Scanner(System.in)) {
            int option;
            do {
                // Display the main menu options
                System.out.println("\n=========================");
                System.out.println("Car Service Management System");
                System.out.println("=========================");
                System.out.println("1. Insert new service");
                System.out.println("2. Delete service by ID");
                System.out.println("3. Search service by ID");
                System.out.println("4. Display all services (In-Order Traversal)");
                System.out.println("5. Display all services (Pre-Order Traversal)");
                System.out.println("6. Display all services (Post-Order Traversal)");
                System.out.println("7. Display all services (Level-Order Traversal)");
                System.out.println("8. Exit and Save");
                System.out.println("Enter your choice (1-8): ");

                // Read the user's choice
                option = scanner.nextInt();
                scanner.nextLine(); // Consume newline character after number input

                // Perform actions based on user's choice
                switch (option) {
                    case 1:
                        insertService(scanner, serviceTree);
                        break;
                    case 2:
                        deleteService(scanner, serviceTree);
                        break;
                    case 3:
                        searchService(scanner, serviceTree);
                        break;
                    case 4:
                        serviceTree.inOrderTraversal();
                        break;
                    case 5:
                        serviceTree.preOrderTraversal();
                        break;
                    case 6:
                        serviceTree.postOrderTraversal();
                        break;
                    case 7:
                        serviceTree.levelOrderTraversal();
                        break;
                    case 8:
                        exitAndSave(serviceTree, FILE_PATH);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

                // Pause after each action until the user presses Enter
                if (option != 8) {
                    System.out.println("Press Enter to continue...");
                    scanner.nextLine();
                }

            } while (option != 8); // Repeat until the user chooses to exit
        } // Scanner will be automatically closed here due to try-with-resources
    }

    // Method to insert a new service into the tree
    private static void insertService(Scanner scanner, BinaryTree serviceTree) {
        // Read service details from user
        System.out.print("Enter service ID: ");
        int serviceId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter service type: ");
        String serviceType = scanner.nextLine();

        System.out.print("Enter total cost: ");
        double totalCost = scanner.nextDouble();
        scanner.nextLine();

        // Insert the service into the tree
        serviceTree.insert(serviceId, make, year, serviceType, totalCost);
        System.out.println("Service inserted successfully.");
    }

    // Method to delete a service from the tree by ID
    private static void deleteService(Scanner scanner, BinaryTree serviceTree) {
        System.out.print("Enter service ID to delete: ");
        int serviceId = scanner.nextInt();
        // Attempt to delete the service and provide feedback
        if (serviceTree.delete(serviceId)) {
            System.out.println("Service deleted successfully.");
        } else {
            System.out.println("Service could not be deleted.");
        }
    }

    private static void searchService(Scanner scanner, BinaryTree serviceTree) {
        System.out.print("Enter service ID to search: ");
        int serviceId = scanner.nextInt();
        // Search for the service and display details if found
        Node serviceNode = serviceTree.search(serviceId);
        if (serviceNode != null) {
            System.out.println("Service found: ");
            printNodeDetails(serviceNode); // Use printNodeDetails to display the node information
        } else {
            System.out.println("Service not found.");
        }
    }

    // make it public
    private static void printNodeDetails(Node node) {
        System.out.println("Service ID: " + node.serviceId);
        System.out.println("Make: " + node.make);
        System.out.println("Year: " + node.year);
        System.out.println("Service Type: " + node.serviceType);
        System.out.println("Total Cost: $" + node.totalCost);
        System.out.println();
    }

    // Method to save data to CSV and exit the program
    private static void exitAndSave(BinaryTree serviceTree, String filePath) {
        saveToCSV(filePath);
        System.out.println("Exiting program.");
    }

    // Method to write the tree data to a CSV file
    private static void saveToCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            bw.write("ServiceId,Make,Year,ServiceType,TotalCost\n");
            System.out.println("Writing data to CSV...");
            // Perform in-order traversal to write data to the file
            serviceTree.writeInOrderToCSV(bw);
            System.out.println("Data successfully saved to CSV.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the CSV file: " + e.getMessage());
        }
    }

    // Method to load data from a CSV file into the tree
    private static void loadCSVData(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip the header row
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Parse and insert each service record into the tree
                int serviceId = Integer.parseInt(values[0].trim());
                String make = values[1].trim();
                int year = Integer.parseInt(values[2].trim());
                String serviceType = values[3].trim().replace("\"", "");
                double totalCost = Double.parseDouble(values[4].trim());
                serviceTree.insert(serviceId, make, year, serviceType, totalCost);
            }
        } catch (FileNotFoundException e) {
            System.err.println("The file was not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing a number from the file: " + e.getMessage());
        }
    }
}
