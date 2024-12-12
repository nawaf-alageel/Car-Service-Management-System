package B_Tree;

public class PrintUtils {
    public static void printCarDetails(Car car) {
        System.out.println("Service ID: " + car.getServiceId());
        System.out.println("Make: " + car.getMake());
        System.out.println("Year: " + car.getYear());
        System.out.println("Service Type: " + car.getServiceType());
        System.out.println("Total Cost: " + car.getTotalCost());
        // Add more details if needed

    }
}
