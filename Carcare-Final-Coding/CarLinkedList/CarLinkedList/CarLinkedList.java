package CarLinkedList;

class CarLinkedList {
    Node head;

    public CarLinkedList() {
        this.head = null;
    }

    public void traverse() {
        if (head == null) {
            System.out.println("No cars in the list.");
            return;
        }
        Node current = head; // Start from the head of the list
        while (current != null) { // Continue until the end of the list
            // Access the fields of the Car object within the current Node
            System.out.println("Service ID: " + current.car.getServiceId()); // Assuming there is a getServiceId()
                                                                             // method in Car
            System.out.println("Make: " + current.car.getMake()); // Assuming there is a getMake() method in Car
            System.out.println("Year: " + current.car.getYear()); // Assuming there is a getYear() method in Car
            System.out.println("Service Type: " + current.car.getServiceType()); // Assuming there is a getServiceType()
                                                                                 // method in Car
            System.out.println("Total Cost: $" + current.car.getTotalCost()); // Assuming there is a getTotalCost()
                                                                              // method in Car
            System.out.println(); // Add a newline for separation
            current = current.next; // Move to the next node
        }
    }

    public void insert(Car car) {
        Node newNode = new Node(car);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public void deleteByServiceId(int serviceId) {
        while (head != null && head.car.getServiceId() == serviceId) {
            head = head.next;
        }

        if (head == null)
            return;

        Node current = head;

        while (current.next != null) {
            if (current.next.car.getServiceId() == serviceId) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }
}
