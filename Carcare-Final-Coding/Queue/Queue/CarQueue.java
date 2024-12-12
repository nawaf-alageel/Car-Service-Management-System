package Queue;

// CarQueue class defines a queue data structure for Car objects.
public class CarQueue {
    private Node front;
    private Node rear;

    // Constructor to initialize an empty queue
    public CarQueue() {
        this.front = this.rear = null;
    }

    // Method to add a new Car object to the queue
    public void enqueue(Car car) {
        Node newNode = new Node(car);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    // Method to remove and return the Car object from the front of the queue
    public Car dequeue() {
        if (front == null) {
            return null;
        }
        // Remove the front node and update front
        Node temp = front;
        front = front.next;

        // If the queue becomes empty, set rear to null as well
        if (front == null) {
            rear = null;
        }
        return temp.car;
    }

    // Method to get (but not remove) the Car object at the front of the queue
    public Car peek() {
        if (front == null) {
            return null;
        }
        return front.car;
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Method to print all Car objects in the queue
    public void printQueue() {
        Node current = front; // Start with the front of the queue
        while (current != null) {
            Car car = current.car; // Directly access the car field of the current node
            System.out.println("Service ID: " + car.getServiceId());
            System.out.println("Make: " + car.getMake());
            System.out.println("Year: " + car.getYear());
            System.out.println("Service Type: " + car.getServiceType());
            System.out.println("Total Cost: $" + car.getTotalCost());
            System.out.println();
            current = current.next; // Move to the next node in the queue
        }
    }
}
