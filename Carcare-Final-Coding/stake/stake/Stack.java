package stake;

public interface Stack {
    void push(Car car);

    Car pop();

    Car peek();

    boolean isEmpty();
}
