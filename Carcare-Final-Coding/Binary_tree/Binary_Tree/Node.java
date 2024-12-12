package Binary_Tree;

public class Node {
    int serviceId;
    String make;
    int year;
    String serviceType;
    double totalCost;
    Node left, right;
    int val;

    Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public Node(int serviceId, String make, int year, String serviceType, double totalCost) {
        this.serviceId = serviceId;
        this.make = make;
        this.year = year;
        this.serviceType = serviceType;
        this.totalCost = totalCost;
        this.left = null;
        this.right = null;
    }

}
