package B_Tree;

public class Car {
    private int serviceId;
    private String make;
    private int year;
    private String serviceType;
    private double totalCost;

    // Constructor
    public Car(int serviceId, String make, int year, String serviceType, double totalCost) {
        this.serviceId = serviceId;
        this.make = make;
        this.year = year;
        this.serviceType = serviceType;
        this.totalCost = totalCost;
    }

    // Getters
    public int getServiceId() {
        return serviceId;
    }

    public String getMake() {
        return make;
    }

    public int getYear() {
        return year;
    }

    public String getServiceType() {
        return serviceType;
    }

    public double getTotalCost() {
        return totalCost;
    }

    // Setters
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    // Elsewhere in your class or as a separate utility class

    public static void printCarDetails(Car car) {
        System.out.println("Service ID: " + car.getServiceId());
        System.out.println("Make: " + car.getMake());
        System.out.println("Year: " + car.getYear());
        System.out.println("Service Type: " + car.getServiceType());
        System.out.println("Total Cost: $" + car.getTotalCost());
        System.out.println(); // For an empty line after car details
    }

    // toString method
    @Override
    public String toString() {
        return "Car{" +
                "serviceId=" + serviceId +
                ", make='" + make + '\'' +
                ", year=" + year +
                ", serviceType='" + serviceType + '\'' +
                ", totalCost=" + totalCost +
                '}';
    }
}
