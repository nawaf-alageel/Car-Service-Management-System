# 🚗 **Car Service Management System** 🚗

This project is a **Car Service Management System** implemented in **Java**. It demonstrates the use of various data structures like **B-Tree**, **Binary Tree**, **Linked List**, **Queue**, and **Stack** to manage car service records. The system allows for operations such as inserting, deleting, searching, and displaying car service data, all of which are stored in CSV files.

---

## 📂 **Project Structure**

# CARCARE-FINAL-CODING
```bash

CARCARE-FINAL-CODING/
├── B_tree/
│ └── B_Tree/
│ ├── BTree.java
│ ├── BTreeMain.java
│ ├── car_care_management_system_dataset.csv
│ ├── Car.java
│ └── PrintUtils.java
├── Binary_tree/
│ └── Binary_Tree/
│ ├── BinaryTree.java
│ ├── BinaryTreeMain.java
│ ├── car_care_management_system_dataset.csv
│ └── Node.java
├── CarLinkedList/
│ └── CarLinkedList/
│ ├── car_care_management_system_dataset.csv
│ ├── Car.java
│ ├── CarLinkedList.java
│ └── CarLinkedListMain.java
├── Queue/
│ └── Queue/
│ ├── car_care_management_system_dataset.csv
│ ├── Car.java
│ ├── CarQueue.java
│ ├── CSVUtils.java
│ ├── Node.java
│ └── QueueMain.java
└── Stack/
└── Stack/
├── car_care_management_system_dataset.csv
├── Car.java
├── Node.java
├── Stack.java
└── StackMain.java


---

## 🛠️ **Features**

### **B-Tree**
- **Files**: `BTree.java`, `BTreeMain.java`, `PrintUtils.java`, `Car.java`
- **Operations**:
  - Insert car records.
  - Delete car records by service ID.
  - Search for car records by service ID.
  - Print the B-Tree structure.
  - Data is saved and loaded from `car_care_management_system_dataset.csv`.

### **Binary Tree**
- **Files**: `BinaryTree.java`, `BinaryTreeMain.java`, `Node.java`
- **Operations**:
  - Insert car records.
  - Delete car records by service ID.
  - Search for car records by service ID.
  - Display records using In-Order, Pre-Order, Post-Order, and Level-Order traversals.
  - Save data back to `car_care_management_system_dataset.csv`.

### **Linked List**
- **Files**: `CarLinkedList.java`, `CarLinkedListMain.java`, `Car.java`
- **Operations**:
  - Insert car records.
  - Delete car records by service ID.
  - Search for car records by service ID.
  - Traverse the linked list.
  - Data is persisted in `car_care_management_system_dataset.csv`.

### **Queue**
- **Files**: `CarQueue.java`, `QueueMain.java`, `CSVUtils.java`, `Car.java`, `Node.java`
- **Operations**:
  - Enqueue (add) a car record.
  - Dequeue (remove) a car record.
  - Peek at the front of the queue.
  - Check if the queue is empty.
  - Print the queue contents.
  - Save data back to `car_care_management_system_dataset.csv`.

### **Stack**
- **Files**: `Stack.java`, `StakeMain.java`, `Car.java`, `Node.java`
- **Operations**:
  - Push (add) a car record onto the stack.
  - Pop (remove) a car record from the stack.
  - Peek at the top of the stack.
  - Check if the stack is empty.
  - Print the stack contents.
  - Save data back to `car_care_management_system_dataset.csv`.

---

## 🚀 **How to Run the Project**

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/CarServiceManagementSystem.git
 ```
 ## 🚀 **Set Up the Project**

1. **Open the project** in your preferred Java IDE (e.g., **IntelliJ IDEA**, **Eclipse**).

2. **Run the Main Classes**:  
   Each package contains a `Main` class that you can run directly:
   - **B-Tree**: `BTreeMain.java`
   - **Binary Tree**: `BinaryTreeMain.java`
   - **Linked List**: `CarLinkedListMain.java`
   - **Queue**: `QueueMain.java`
   - **Stack**: `StakeMain.java`

3. **Dataset**:  
   The car service records are stored in `car_care_management_system_dataset.csv` located in each package.

---

## 📊 **Sample Data Format**

Each CSV file contains car service data in the following format:

```sql
ServiceID,Make,Year,ServiceType,TotalCost
1,Toyota,2020,Oil Change,50.0
2,Honda,2019,Brake Replacement,200.0
3,Ford,2021,Tire Rotation,30.0

```
## 👥 **Contributor**

- **Nawaf Abdulrhman Alageel**: B-Tree Implementation, Queue and Stack Implementation, Binary Tree Implementation, Linked List Implementation, Documentation, and Code Maintenance

---

## 📝 **License**

This project is licensed under the **MIT License**.

---

## 💬 **Feedback and Contributions**

Feel free to **fork** the repository and submit **pull requests**.  
If you encounter any issues or have feature requests, please open an **issue** on the repository.

