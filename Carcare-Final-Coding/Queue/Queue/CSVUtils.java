package Queue;

import java.io.*;

public class CSVUtils {

    public static void loadCarsFromCSV(String filePath, CarQueue carQueue) {
        System.out.println("Loading cars from CSV...");
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("CSV file does not exist: " + filePath);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int serviceId = Integer.parseInt(values[0].trim());
                String make = values[1].trim();
                int year = Integer.parseInt(values[2].trim());
                String serviceType = values[3].trim();
                double totalCost = Double.parseDouble(values[4].trim());
                carQueue.enqueue(new Car(serviceId, make, year, serviceType, totalCost));
                // System.out.println("Loaded car: " + make + " " + year); // Comment out or
                // remove this line
            }
            System.out.println("Load successful."); // Add this line to print a success message after loading is
                                                    // complete
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeCarsToCSV(String filePath, CarQueue carQueue) {
        System.out.println("Writing cars to CSV...");
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            writer.println("ServiceId,Make,Year,ServiceType,TotalCost");
            while (!carQueue.isEmpty()) {
                Car car = carQueue.dequeue();
                writer.println(car.getServiceId() + "," + car.getMake() + "," + car.getYear() + ","
                        + car.getServiceType() + "," + car.getTotalCost());
                // Note: This will empty the queue. If you need to preserve the queue, use a
                // temporary list.
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}