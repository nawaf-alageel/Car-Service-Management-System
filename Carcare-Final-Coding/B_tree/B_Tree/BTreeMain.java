    package B_Tree;

    import java.io.PrintWriter;
    import java.io.PrintWriter;
    import B_Tree.PrintUtils;
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;

    @SuppressWarnings("unused")
    public class BTreeMain {

        public static void main(String[] args) {
            String filePath = "B_tree\\B_Tree\\car_care_management_system_dataset.csv";
            BTree bTree = new BTree(43); // Assuming the degree is still relevant

            // Read data from the file and insert into B-Tree
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                br.readLine(); // Skip header line

                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    // Process the values and create a Car object
                    Car car = createCarFromValues(values);
                    bTree.insert(car);
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;
            while (!exit) {
                System.out.println("=============================================================================");
                System.out.println("Choose B-Tree operation:");
                System.out.println("1. Insert");
                System.out.println("2. Delete");
                System.out.println("3. Search");
                System.out.println("4. Print B-Tree");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        Car newCar = promptForNewCar(scanner);
                        bTree.insert(newCar);
                        writeCarToCSV(filePath, newCar);
                        System.out.println("Car inserted and written to CSV file.");
                        ;
                        break;

                    case 2: // Delete a Car
                        System.out.print("Enter the service ID of the car to delete: ");
                        int serviceIdToDelete = scanner.nextInt();
                        Car carToDelete = bTree.searchByServiceId(serviceIdToDelete);
                        if (carToDelete != null) {
                            bTree.delete(carToDelete);
                            System.out.println("Car deleted successfully.");
                            removeCarFromCSV(filePath, serviceIdToDelete); // Remove the car from the CSV file
                        } else {
                            System.out.println("Car with service ID " + serviceIdToDelete + " not found.");
                        }

                        System.out.println("=============================================================================");
                        break;
                    case 3: // Search for a Car
                        System.out.print("Enter the service ID of the car to search: ");
                        int serviceIdToSearch = scanner.nextInt();
                        // Assuming there is a method to find a Car by service ID in the BTree
                        Car carToSearch = bTree.searchByServiceId(serviceIdToSearch);
                        if (carToSearch != null) {
                            System.out.println("Car found:");
                            PrintUtils.printCarDetails(carToSearch); // Assuming PrintUtils is properly imported and
                                                                    // accessible
                        } else {
                            System.out.println("Car not found.");
                        }
                        System.out.println("=============================================================================");
                        break;

                    case 4:
                        bTree.printBTree(); // Assuming the printBTree method is implemented in the BTree class
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }

            scanner.close();
        }

        // Method to remove the car with a specific service ID from the CSV file
        public static void removeCarFromCSV(String filePath, int serviceId) {
            List<Car> remainingCars = new ArrayList<>();

            // Read all cars from the file except the one to delete
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line = br.readLine(); // Read header line

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    Car car = createCarFromValues(values);
                    if (car.getServiceId() != serviceId) {
                        remainingCars.add(car);
                    }
                }
            } catch (IOException e) {
                System.err.println("An error occurred while reading the file.");
                e.printStackTrace();
            }

            // Write the remaining cars back to the CSV
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
                out.println("ServiceID,Make,Year,ServiceType,TotalCost"); // Write header
                for (Car car : remainingCars) {
                    out.println(carToCSVString(car));
                }
            } catch (IOException e) {
                System.err.println("An error occurred while recreating the CSV file.");
                e.printStackTrace();
            }
        }

        // Method to write a Car object to the CSV file
        public static void writeCarToCSV(String filePath, Car car) {
            try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)))) {
                out.println(carToCSVString(car));
            } catch (IOException e) {
                System.err.println("An error occurred while writing the car to the file.");
                e.printStackTrace();
            }
        }

        // Method to prompt for new car details and create a Car object
        private static Car promptForNewCar(Scanner scanner) {
            System.out.print("Enter service ID: ");
            int serviceId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter make: ");
            String make = scanner.nextLine();
            System.out.print("Enter year: ");
            int year = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter service type: ");
            String serviceType = scanner.nextLine();
            System.out.print("Enter total cost: ");
            double totalCost = scanner.nextDouble();

            return new Car(serviceId, make, year, serviceType, totalCost);
        }

        // Method to create a Car from string array values
        private static Car createCarFromValues(String[] values) {
            int serviceId = Integer.parseInt(values[0].trim());
            String make = values[1].trim();
            int year = Integer.parseInt(values[2].trim());
            String serviceType = values[3].trim();
            double totalCost = Double.parseDouble(values[4].trim());

            return new Car(serviceId, make, year, serviceType, totalCost);
        }

        // Helper method to convert a Car object to a CSV-formatted string
        public static String carToCSVString(Car car) {
            return car.getServiceId() + "," +
                    car.getMake() + "," +
                    car.getYear() + "," +
                    car.getServiceType() + "," +
                    car.getTotalCost();
        }
    }
