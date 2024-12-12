package B_Tree;

import java.util.LinkedList;
import java.util.Queue;

class BTree {
    private Node root;
    private int degree;

    public BTree(int degree) {
        this.degree = degree;
        this.root = new Node(true);
    }

    // Insert a Car into the B-Tree
    public void insert(Car car) {
        Node r = root;
        if (r.numKeys == 2 * degree - 1) {
            Node s = new Node(false);
            root = s;
            s.children[0] = r;
            s.splitChild(0, r);
            s.insertNonFull(car);
        } else {
            r.insertNonFull(car);
        }
    }

    public void printBTree() {
        Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                for (int i = 0; i < levelSize; i++) {
                    Node currentNode = queue.poll();

                    // Print details of each car in the node, or indicate if the car is null
                    for (Car car : currentNode.keys) {
                        if (car != null) {
                            try {
                                PrintUtils.printCarDetails(car); // Print car details
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                                System.out.println("Caught NullPointerException for car: " + car);
                            }
                        } else {
                        }
                    }

                    // Separator between nodes at the same level

                    // Add all non-null children of the current node to the queue
                    for (Node child : currentNode.children) {
                        if (child != null) {
                            queue.add(child);
                        }
                    }
                }
                // Separator between levels
                System.out.println("=========================================================");
            }
        } else {
            System.out.println("The B-Tree is empty.");
        }
    }

    // Method to search for a Car by its serviceId
    public Car searchByServiceId(int serviceId) {
        if (root != null) {
            return root.searchByServiceId(serviceId);
        }
        return null;
    }

    // Method to delete a car from the B-Tree
    public void delete(Car car) {
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        root.delete(car);

        // If the root node has 0 keys, make its first child the new root if it has a
        // child.
        if (root.numKeys == 0) {
            root = root.leaf ? null : root.children[0];
        }
    }

    private class Node {
        Car[] keys; // array of cars
        Node[] children; // children of the node
        boolean leaf; // is node a leaf
        int numKeys; // current number of keys
        @SuppressWarnings("unused")
        boolean isLeaf; // Redundant flag (identical to 'leaf'), should be removed

        // Node constructor
        public Node(boolean isLeafNode) {
            leaf = isLeafNode;
            keys = new Car[2 * degree - 1];
            children = new Node[2 * degree];
            numKeys = 0;
        }

        void insertNonFull(Car car) {
            int i = numKeys - 1;
            if (leaf) {
                while (i >= 0 && keys[i].getServiceId() > car.getServiceId()) {
                    keys[i + 1] = keys[i];
                    i--;
                }
                keys[i + 1] = car;
                numKeys++;
            } else {
                while (i >= 0 && keys[i].getServiceId() > car.getServiceId()) {
                    i--;
                }
                if (children[i + 1].numKeys == 2 * degree - 1) {
                    splitChild(i + 1, children[i + 1]);
                    if (keys[i + 1].getServiceId() < car.getServiceId()) {
                        i++;
                    }
                }
                children[i + 1].insertNonFull(car);
            }
        }

        void splitChild(int i, Node y) {
            Node z = new Node(y.leaf);
            z.numKeys = degree - 1;
            for (int j = 0; j < degree - 1; j++) {
                z.keys[j] = y.keys[j + degree];
            }
            if (!y.leaf) {
                for (int j = 0; j < degree; j++) {
                    z.children[j] = y.children[j + degree];
                }
            }
            y.numKeys = degree - 1;
            for (int j = numKeys; j >= i + 1; j--) {
                children[j + 1] = children[j];
            }
            children[i + 1] = z;
            for (int j = numKeys - 1; j >= i; j--) {
                keys[j + 1] = keys[j];
            }
            keys[i] = y.keys[degree - 1];
            numKeys++;
        }
        // Recur on the last subtree

        public Car searchByServiceId(int serviceId) {
            int i = 0;

            // Find the first key greater than or equal to serviceId
            while (i < numKeys && keys[i].getServiceId() < serviceId) {
                i++;
            }

            // Check if the found key is equal to serviceId, return this car
            if (i < numKeys && keys[i].getServiceId() == serviceId) {
                return keys[i];
            }

            // If the key is not found and this is a leaf node, the car is not present
            if (leaf) { // Use the 'leaf' variable instead of 'isLeaf'
                return null;
            }

            // Go to the appropriate child node and continue the search
            return children[i].searchByServiceId(serviceId);
        }

        public void delete(Car car) {
            int idx = findKeyIndex(car.getServiceId());

            // The key to be removed is present in this node
            if (idx < numKeys && keys[idx].getServiceId() == car.getServiceId()) {
                // The node is a leaf node - remove the key from this node
                if (leaf) {
                    removeKey(idx);
                } else { // The node is an internal node
                    deleteFromInternalNode(idx);
                }
            } else { // The key is not present in this node
                if (leaf) { // If the tree has only one node and the key is not present in it
                    System.out.println("The car is not present in the tree.");
                    return;
                }

                // The key to be removed is present in the sub-tree rooted at this node
                // The flag indicates whether the key is present in the sub-tree rooted
                // at the last child of this node
                boolean flag = ((idx == numKeys) ? true : false);

                // If the child where the key is supposed to exist has less than 't' keys,
                // we fill that child
                if (children[idx].numKeys < degree) {
                    fill(idx);
                }

                // If the last child has been merged, it must have been merged with the previous
                // child and so we recurse on the (idx-1)th child. Else, we recurse on the
                // (idx)th
                // child which now has atleast 't' keys
                if (flag && idx > numKeys) {
                    children[idx - 1].delete(car);
                } else {
                    children[idx].delete(car);
                }
            }
        }

        private void deleteFromInternalNode(int idx) {
            Car k = keys[idx];

            // If the child that precedes k (children[idx]) has at least 't' keys,
            // find the predecessor 'pred' of k in the subtree rooted at
            // children[idx]. Replace k by pred. Recursively delete pred in children[idx]
            if (!children[idx].leaf && children[idx].numKeys >= degree) {
                Car pred = getPredecessor(idx);
                keys[idx] = pred;
                children[idx].delete(pred);
            }
            // If children[idx] has less that 't' keys, examine children[idx+1].
            // If children[idx+1] has atleast 't' keys, find the successor 'succ' of k in
            // the subtree rooted at children[idx+1]
            else if (!children[idx + 1].leaf && children[idx + 1].numKeys >= degree) {
                Car succ = getSuccessor(idx);
                keys[idx] = succ;
                children[idx + 1].delete(succ);
            }
            // If both children[idx] and children[idx+1] has less than 't' keys,merge k and
            // all of children[idx+1]
            // into children[idx]
            // Now children[idx] contains 2t-1 keys
            // Free children[idx+1] and recursively delete k from children[idx]
            else {
                merge(idx);
                children[idx].delete(k);
            }
        }

        // A function to remove the idx-th key from this node - which is a leaf node
        private void removeKey(int idx) {
            // Shift all the keys after the idx-th pos one place backward
            for (int i = idx + 1; i < numKeys; ++i)
                keys[i - 1] = keys[i];

            // Reduce the count of keys
            numKeys--;
        }

        // A function to get the predecessor of the key- where the key
        // is present in the idx-th position in the node
        private Car getPredecessor(int idx) {
            // Keep moving to the right most node until we reach a leaf
            Node cur = children[idx];
            while (!cur.leaf)
                cur = cur.children[cur.numKeys];

            // Return the last key of the leaf
            return cur.keys[cur.numKeys - 1];
        }

        private Car getSuccessor(int idx) {
            // Keep moving the left most node starting from children[idx+1] until we reach a
            // leaf
            Node cur = children[idx + 1];
            while (!cur.leaf)
                cur = cur.children[0];

            // Return the first key of the leaf
            return cur.keys[0];
        }

        // A function to fill child children[idx] which has less than t-1 keys
        private void fill(int idx) {
            // If the previous child(children[idx-1]) has more than t-1 keys, borrow a key
            // from that child
            if (idx != 0 && children[idx - 1].numKeys >= degree)
                borrowFromPrev(idx);

            // If the next child(children[idx+1]) has more than t-1 keys, borrow a key
            // from that child
            else if (idx != numKeys && children[idx + 1].numKeys >= degree)
                borrowFromNext(idx);

            // Merge children[idx] with its sibling
            // If children[idx] is the last child, merge it with with its previous sibling
            // Otherwise merge it with its next sibling
            else {
                if (idx != numKeys)
                    merge(idx);
                else
                    merge(idx - 1);
            }
        }

        // A function to borrow a key from children[idx-1] and insert it
        // into children[idx]
        private void borrowFromPrev(int idx) {

            Node child = children[idx];
            Node sibling = children[idx - 1];

            // The last key from children[idx-1] goes up to the parent and key[idx-1]
            // from parent is inserted as the first key in children[idx]. Thus, the loses
            // sibling one key and child gains one key

            // Moving all key in children[idx] one step ahead
            for (int i = child.numKeys - 1; i >= 0; --i)
                child.keys[i + 1] = child.keys[i];

            // If children[idx] is not a leaf, move all its child pointers one step ahead
            if (!child.leaf) {
                for (int i = child.numKeys; i >= 0; --i)
                    child.children[i + 1] = child.children[i];
            }

            // Setting child's first key equal to keys[idx-1] from the current node
            child.keys[0] = keys[idx - 1];

            // Moving sibling's last child as children[idx]'s first child
            if (!child.leaf)
                child.children[0] = sibling.children[sibling.numKeys];

            // Moving the key from the sibling to the parent
            // This reduces the number of keys in the sibling
            keys[idx - 1] = sibling.keys[sibling.numKeys - 1];

            child.numKeys += 1;
            sibling.numKeys -= 1;
        }

        // A function to borrow a key from the children[idx+1] and place
        // it in children[idx]
        private void borrowFromNext(int idx) {

            Node child = children[idx];
            Node sibling = children[idx + 1];

            // keys[idx] is inserted as the last key in children[idx]
            child.keys[child.numKeys] = keys[idx];

            // Sibling's first child is inserted as the last child
            // into children[idx]
            if (!(child.leaf))
                child.children[child.numKeys + 1] = sibling.children[0];

            // The first key from sibling is inserted into keys[idx]
            keys[idx] = sibling.keys[0];

            // Moving all keys in sibling one step behind
            for (int i = 1; i < sibling.numKeys; ++i)
                sibling.keys[i - 1] = sibling.keys[i];

            // Moving the child pointers one step behind
            if (!sibling.leaf) {
                for (int i = 1; i <= sibling.numKeys; ++i)
                    sibling.children[i - 1] = sibling.children[i];
            }

            // Increasing and decreasing the key count of children[idx] and children[idx+1]
            // respectively
            child.numKeys += 1;
            sibling.numKeys -= 1;
        }

        // A function to merge children[idx] with children[idx+1]
        // children[idx+1] is freed after merging
        private void merge(int idx) {
            Node child = children[idx];
            Node sibling = children[idx + 1];

            // Pulling a key from the current node and inserting it into (t-1)th
            // position of children[idx]
            child.keys[degree - 1] = keys[idx];

            // Copying the keys from children[idx+1] to children[idx] at the end
            for (int i = 0; i < sibling.numKeys; ++i)
                child.keys[i + degree] = sibling.keys[i];

            // Copying the child pointers from children[idx+1] to children[idx]
            if (!child.leaf) {
                for (int i = 0; i <= sibling.numKeys; ++i)
                    child.children[i + degree] = sibling.children[i];
            }

            // Moving all keys after idx in the current node one step before -
            // to fill the gap created by moving keys[idx] to children[idx]
            for (int i = idx + 1; i < numKeys; ++i)
                keys[i - 1] = keys[i];

            // Moving the child pointers after (idx+1) in the current node one
            // step before
            for (int i = idx + 2; i <= numKeys; ++i)
                children[i - 1] = children[i];

            // Updating the key count of child and the current node
            child.numKeys += sibling.numKeys + 1;
            numKeys--;

            // Freeing the memory occupied by sibling
        }

        // The function to find the index of the first key that is greater or equal to k
        private int findKeyIndex(int k) {
            int idx = 0;
            while (idx < numKeys && keys[idx].getServiceId() < k)
                ++idx;
            return idx;

        }

    }
}
