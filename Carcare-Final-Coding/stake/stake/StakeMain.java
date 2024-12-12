package stake;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CarStack carStack = new CarStack();
        loadCarsFromCSV("stake\\stake\\car_care_management_system_dataset.csv", carStack);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();
            System.out.println("\n=========================");
            System.out.println("Car Service Management System");
            System.out.println("=========================");
            System.out.println("Menu:");
            System.out.println("1. Push a Car");
            System.out.println("2. Pop a Car");
            System.out.println("3. Peek at the top Car");
            System.out.println("4. Check if Stack is Empty");
            System.out.println("5. Print the stack content");
            System.out.println("6. Exit and Save");
            System.out.print("Enter your choice (1-6): ");

            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid number.");
                scanner.next(); // to clear the scanner's buffer
                continue;
            }

            switch (choice) {
                case 1:
                    insertCar(scanner, carStack);
                    break;
                case 2:
                    Car poppedCar = carStack.pop();
                    if (poppedCar != null) {
                        System.out.println("Popped car: " + poppedCar);
                    }
                    break;
                case 3:
                    Car topCar = carStack.peek();
                    if (topCar != null) {
                        System.out.println("Top car: " + topCar);
                    }
                    break;
                case 4:
                    System.out.println("Is stack empty? " + carStack.isEmpty());
                    break;
                case 5:
                    printStack(carStack);
                    break;
                case 6:
                    writeCarsToCSV("stake\\car_care_management_system_dataset.csv", carStack);
                    System.out.println("Data saved. Exiting...");
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

    private static void writeCarsToCSV(String filePath, CarStack carStack) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ServiceId,Make,Year,ServiceType,TotalCost\n");
            CarStack tempStack = new CarStack(); // Temporary stack to reverse the order

            // Pop all items from the carStack and push them onto tempStack to reverse the
            // order
            while (!carStack.isEmpty()) {
                tempStack.push(carStack.pop());
            }

            // Now pop from tempStack (which is in original order) and write to file

            while (!tempStack.isEmpty()) {
                Car car = tempStack.pop();
                // Use the getters from the Car class to access the private fields
                writer.write(car.getServiceId() + "," + car.getMake() + "," + car.getYear() + "," + car.getServiceType()
                        + "," + car.getTotalCost() + "\n");
                carStack.push(car); // Push back onto carStack to maintain original stack
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the CSV file: " + e.getMessage());
        }
    }

    private static void loadCarsFromCSV(String filePath, CarStack carStack) {
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
                carStack.push(new Car(serviceId, make, year, serviceType, totalCost));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printStack(CarStack carStack) {
        System.out.println("Stack content (top to bottom):");
        carStack.printStack(); // This calls the printStack method of CarStack class
    }

    private static void insertCar(Scanner scanner, CarStack carStack) {
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
        carStack.push(new Car(serviceId, make, year, serviceType, totalCost));
        System.out.println("Car pushed successfully.");
    }

    private static void clearScreen() {
        // Clear the screen in the console
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error clearing the screen: " + e.getMessage());
        }
    }
}