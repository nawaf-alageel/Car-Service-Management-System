package Queue;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        CarQueue carQueue = new CarQueue();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        CSVUtils.loadCarsFromCSV("Queue\\Queue\\car_care_management_system_dataset.csv", carQueue);
        while (!exit) {
            System.out.println("\n=========================");
            System.out.println("Car Service Management System");
            System.out.println("=========================");
            System.out.println("Menu:");
            System.out.println("1. Enqueue a Car");
            System.out.println("2. Dequeue a Car");
            System.out.println("3. Peek at the front Car");
            System.out.println("4. Check if Queue is Empty");
            System.out.println("5. Print the queue content");
            System.out.println("6. Exit and Save");
            System.out.print("Enter your choice (1-6): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    enqueueCar(scanner, carQueue);
                    break;
                case 2:
                    dequeueCar(carQueue);
                    break;
                case 3:
                    peekFrontCar(carQueue);
                    break;
                case 4:
                    checkIfQueueIsEmpty(carQueue);
                    break;
                case 5:
                    printQueue(carQueue);
                    break;
                case 6:
                    saveAndExit(carQueue);
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            if (!exit) {
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); // Wait for the user to press Enter
            }
        }

        scanner.close();
    }

    private static void enqueueCar(Scanner scanner, CarQueue carQueue) {
        System.out.print("Enter Service ID: ");
        int serviceId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Service Type: ");
        String serviceType = scanner.nextLine();
        System.out.print("Enter Total Cost: ");
        double totalCost = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Car car = new Car(serviceId, make, year, serviceType, totalCost);
        carQueue.enqueue(car);
        System.out.println("Car enqueued successfully.");
    }

    private static void dequeueCar(CarQueue carQueue) {
        Car car = carQueue.dequeue();
        if (car != null) {
            System.out.println("Car dequeued: " + car);
        } else {
            System.out.println("Queue is empty.");
        }
    }

    private static void peekFrontCar(CarQueue carQueue) {
        Car car = carQueue.peek();
        if (car != null) {
            System.out.println("Front car: " + car);
        } else {
            System.out.println("Queue is empty.");
        }
    }

    private static void checkIfQueueIsEmpty(CarQueue carQueue) {
        boolean isEmpty = carQueue.isEmpty();
        System.out.println("Queue is empty: " + isEmpty);
    }

    private static void printQueue(CarQueue carQueue) {
        System.out.println("Queue content (front to rear):");
        carQueue.printQueue();
    }

    private static void saveAndExit(CarQueue carQueue) {
        CSVUtils.writeCarsToCSV("Queue/car_care_management_system_dataset.csv", carQueue);
        System.out.println("Data saved. Exiting...");
    }
}