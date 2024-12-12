package stake;

public class Car {
    private int serviceId;
    private String make;
    private int year;
    private String serviceType;
    private double totalCost;

    // Constructor for the Car class
    public Car(int serviceId, String make, int year, String serviceType, double totalCost) {
        this.serviceId = serviceId;
        this.make = make;
        this.year = year;
        this.serviceType = serviceType;
        this.totalCost = totalCost;
    }

    // Getter methods for each field
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
