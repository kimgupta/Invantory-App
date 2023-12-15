import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Product {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

class InventoryManagementSystem {
    private Map<String, Product> products;
    private Scanner scanner;

    public InventoryManagementSystem() {
        this.products = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    updateProduct();
                    break;
                case 3:
                    removeProduct();
                    break;
                case 4:
                    displayInventory();
                    break;
                case 5:
                    recordSale();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting Inventory Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n1. Add Product\n2. Update Product\n3. Remove Product\n4. Display Inventory\n5. Record Sale\n6. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }

    private void addProduct() {
        System.out.print("Enter product name: ");
        String productName = scanner.next();
        System.out.print("Enter product price: ");
        double productPrice = getValidDoubleInput();
        System.out.print("Enter product quantity: ");
        int productQuantity = getValidIntInput();
        products.put(productName, new Product(productName, productPrice, productQuantity));
        System.out.println("Product added successfully.");
    }

    private void updateProduct() {
        System.out.print("Enter product name to update: ");
        String updateProductName = scanner.next();
        if (products.containsKey(updateProductName)) {
            System.out.print("Enter updated price: ");
            double updateProductPrice = getValidDoubleInput();
            System.out.print("Enter updated quantity: ");
            int updateProductQuantity = getValidIntInput();
            Product product = products.get(updateProductName);
            product.price = updateProductPrice;
            product.quantity = updateProductQuantity;
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void removeProduct() {
        System.out.print("Enter product name to remove: ");
        String removeProductName = scanner.next();
        if (products.containsKey(removeProductName)) {
            products.remove(removeProductName);
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    private void displayInventory() {
        System.out.println("Inventory:");
        for (Product product : products.values()) {
            System.out.println(product.name + " - Price: $" + product.price + " - Quantity: " + product.quantity);
        }
    }

    private void recordSale() {
        System.out.print("Enter product name for sale: ");
        String saleProductName = scanner.next();
        if (products.containsKey(saleProductName)) {
            Product product = products.get(saleProductName);
            System.out.print("Enter quantity sold: ");
            int saleQuantity = getValidIntInput();
            if (product.quantity >= saleQuantity) {
                product.quantity -= saleQuantity;
                System.out.println("Sale recorded successfully.");
            } else {
                System.out.println("Not enough stock available for the sale.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    private double getValidDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number for price.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextDouble();
    }

    private int getValidIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number for quantity.");
            scanner.next(); // consume the invalid input
        }
        return scanner.nextInt();
    }
}

public class InventoryApp {
    public static void main(String[] args) {
        InventoryManagementSystem inventorySystem = new InventoryManagementSystem();
        inventorySystem.start();
    }
}
