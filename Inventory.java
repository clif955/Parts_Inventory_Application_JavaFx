package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    public Inventory() {
    }
    // set all parts method
    public static void setAllParts(ObservableList<Part> allParts) {
        Inventory.allParts = allParts;
    }
    // set all products method
    public static void setAllProducts(ObservableList<Product> allProducts) {
        Inventory.allProducts = allProducts;
    }
    // get all Parts
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    // get all Products
    public static ObservableList<Product> getAllProducts() {

        return allProducts;
    }

    // all Parts collections array
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    // all Products collections array
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    // add part method
    public static void addPart(Part newPart) {

        allParts.add(newPart);
    }
    // add Product method
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);

    }
    // lookup or search by name and id for part
    public static void lookupPart(int partID) {
        allParts.get(partID);

    }
    // lookup or search by name and id for product
    public static void lookupProduct(int productID) {
        allProducts.get(productID);
    }
    // lookup Part array
    public static ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> Pt = FXCollections.observableArrayList();
        for (int i = 0; i < Pt.size(); i++) {
            if (allParts.get(i).getName().contains(partName)) {
                Pt.add(allParts.get(i));
            }
        }
        return Pt;
    }
    // lookup Product array
    public static ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> Pr = FXCollections.observableArrayList();
        for (int i = 0; i < Pr.size(); i++) {
            if (allProducts.get(i).getName().contains(productName)) {
                Pr.add(allProducts.get(i));
            }
        }
        return Pr;
    }

    public static void updatePart(int index, Part selectedPart) {

    }

    public static void updateProduct(int index, Product newProduct) {

    }
    // delete part method
    public static boolean deletePart(Part selectedPart) {
        return allParts.remove(selectedPart);

    }
    // delete product method
    public static boolean deleteProduct(Product selectedProduct) {

        return allProducts.add(selectedProduct);
    }
}



