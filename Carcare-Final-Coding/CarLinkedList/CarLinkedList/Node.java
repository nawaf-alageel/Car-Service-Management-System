
package CarLinkedList;

class Node {
    Car car;
    Node next;

    public Node(Car car) {
        this.car = car;
        this.next = null;
    }

    // Assuming the Car class has the following getter methods:
    // getServiceId(), getMake(), getYear(), getServiceType(), getTotalCost()

    public int getServiceId() {
        return car.getServiceId();
    }

    public String getMake() {
        return car.getMake();
    }

    public int getYear() {
        return car.getYear();
    }

    public String getServiceType() {
        return car.getServiceType();
    }

    public double getTotalCost() {
        return car.getTotalCost();
    }

    // If you also want to set values, you can add setters here that delegate to the
    // Car object
    // ...

    // You may include additional methods or logic as required for your Node class
    // ...
}
