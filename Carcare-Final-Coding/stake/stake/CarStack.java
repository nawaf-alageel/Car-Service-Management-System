package stake;

public class CarStack implements Stack {
    private Node top;

    public void printStack() {
        Node current = top; // Assuming 'top' is the name of the top node in the stack
        while (current != null) {
            Car car = current.car; // Assuming 'car' is the object stored at each Node
            // Use the getters from the Car class to access the private fields
            System.out.println("Service ID: " + car.getServiceId());
            System.out.println("Make: " + car.getMake());
            System.out.println("Year: " + car.getYear());
            System.out.println("Service Type: " + car.getServiceType());
            System.out.println("Total Cost: $" + car.getTotalCost());
            System.out.println(); // Add a newline for separation
            current = current.next; // Move to the next node
        }
    }

    @Override
    public void push(Car car) {
        Node newNode = new Node(car);
        newNode.next = top;
        top = newNode;
    }

    @Override
    public Car pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        Car car = top.car;
        top = top.next;
        return car;
    }

    @Override
    public Car peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return null;
        }
        return top.car;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    public Node getTop() {
        return top;
    }

    public void setTop(Node top) {
        this.top = top;
    }

}
