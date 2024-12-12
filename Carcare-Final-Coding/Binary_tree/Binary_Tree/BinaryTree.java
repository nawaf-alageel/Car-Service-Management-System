package Binary_Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.io.BufferedWriter;
import java.io.IOException;

public class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(int serviceId, String make, int year, String serviceType, double totalCost) {
        root = insertRecursive(root, serviceId, make, year, serviceType, totalCost);
    }

    private Node insertRecursive(Node current, int serviceId, String make, int year, String serviceType,
            double totalCost) {
        if (current == null) {
            return new Node(serviceId, make, year, serviceType, totalCost);
        }

        if (serviceId < current.serviceId) {
            current.left = insertRecursive(current.left, serviceId, make, year, serviceType, totalCost);
        } else if (serviceId > current.serviceId) {
            current.right = insertRecursive(current.right, serviceId, make, year, serviceType, totalCost);
        } else {
            // Node already exists
            return current;
        }

        return current;
    }

    // Method to search for a node by serviceId
    public Node search(int serviceId) {
        return searchRecursive(root, serviceId);
    }

    private Node searchRecursive(Node current, int serviceId) {
        if (current == null || current.serviceId == serviceId) {
            return current;
        }
        if (serviceId < current.serviceId) {
            return searchRecursive(current.left, serviceId);
        } else {
            return searchRecursive(current.right, serviceId);
        }
    }

    // Method to delete a node by serviceId, returns true if deletion is successful
    public boolean delete(int serviceId) {
        if (search(serviceId) == null) {
            // Node doesn't exist
            return false;
        }
        root = deleteRecursive(root, serviceId);
        return true;
    }

    private Node deleteRecursive(Node current, int serviceId) {
        if (current == null) {
            return null;
        }

        if (serviceId == current.serviceId) {
            // Node to delete found
            // Node with only one child or no child
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }
            // Nodes with two children: Get inorder successor (smallest in the right
            // subtree)
            current.serviceId = minValue(current.right);
            // Delete the inorder successor
            current.right = deleteRecursive(current.right, current.serviceId);
        } else if (serviceId < current.serviceId) {
            current.left = deleteRecursive(current.left, serviceId);
        } else {
            current.right = deleteRecursive(current.right, serviceId);
        }
        return current;
    }

    private int minValue(Node root) {
        int minValue = root.serviceId;
        while (root.left != null) {
            minValue = root.left.serviceId;
            root = root.left;
        }
        return minValue;
    }

    public void inOrderTraversal() {
        inOrderTraversalRecursive(root);
    }

    private void inOrderTraversalRecursive(Node node) {
        if (node != null) {
            inOrderTraversalRecursive(node.left);
            printNodeDetails(node);
            inOrderTraversalRecursive(node.right);
        }
    }

    public void preOrderTraversal() {
        preOrderTraversalRecursive(root);
    }

    private void preOrderTraversalRecursive(Node node) {
        if (node != null) {
            printNodeDetails(node);
            preOrderTraversalRecursive(node.left);
            preOrderTraversalRecursive(node.right);
        }
    }

    public void postOrderTraversal() {
        postOrderTraversalRecursive(root);
    }

    private void postOrderTraversalRecursive(Node node) {
        if (node != null) {
            postOrderTraversalRecursive(node.left);
            postOrderTraversalRecursive(node.right);
            printNodeDetails(node);
        }
    }

    // Revised Level Order Traversal using a Queue
    public void levelOrderTraversal() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            printNodeDetails(node);

            if (node.left != null) {
                queue.add(node.left);
            }

            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    // Method to write the binary tree in in-order to a CSV file
    public void writeInOrderToCSV(BufferedWriter bw) throws IOException {
        writeInOrderToCSVRecursive(root, bw);
    }

    // Helper method for recursive in-order writing to a CSV file
    private void writeInOrderToCSVRecursive(Node node, BufferedWriter bw) throws IOException {
        if (node != null) {
            writeInOrderToCSVRecursive(node.left, bw);
            bw.write(node.serviceId + "," + node.make + "," + node.year + "," + node.serviceType + "," + node.totalCost
                    + "\n");
            writeInOrderToCSVRecursive(node.right, bw);
        }
    }

    // Method to print details of a node
    private void printNodeDetails(Node node) {
        System.out.println("Service ID: " + node.serviceId);
        System.out.println("Make: " + node.make);
        System.out.println("Year: " + node.year);
        System.out.println("Service Type: " + node.serviceType);
        System.out.println("Total Cost: $" + node.totalCost);
        System.out.println();
    }
}
