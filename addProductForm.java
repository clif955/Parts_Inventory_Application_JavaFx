package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.net.URL;
import java.util.ResourceBundle;

//import static model.Product.associatedProducts;

public class addProductForm implements Initializable {
    static int autoId = 4;
    @FXML
    private TableView<Part> part_Tableview1;
    @FXML
    public TableColumn<Part, Integer> partID;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, Integer> inventoryLevel;
    @FXML
    private TableColumn<Part, Double> priceCost;
    @FXML
    private TableView<Part> aPart_Tableview;
    @FXML
    public TableColumn<Part, Integer> aPartID;
    @FXML
    private TableColumn<Part, String> aPartName;
    @FXML
    private TableColumn<Part, Integer> aInventoryLevel;
    @FXML
    private TableColumn<Part, Double> aPriceCost;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField stock;
    @FXML
    private TextField price;
    @FXML
    private TextField max;
    @FXML
    private TextField min;

    @FXML
    private TextField addProductSearch;
    @FXML
    private Button removePart;
    @FXML
    private Button Add;
    @FXML
    private Button Save;
    @FXML
    private Button Cancel;
    // collections array for associatePart and Products
    ObservableList<Part> associatePart = FXCollections.observableArrayList();
    ObservableList<Product> associateProducts = FXCollections.observableArrayList();

    // set up the part tableview and the table columns
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        part_Tableview1.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
    // set up the 2nd tableview and the table columns
        aPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        aPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        aPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        aInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }
    // populate text fields
    public void sendPart(Product part) {
        id.setText(String.valueOf(part.getId()));
        name.setText(String.valueOf(part.getName()));
        stock.setText(String.valueOf(part.getStock()));
        price.setText(String.valueOf(part.getPrice()));
        max.setText(String.valueOf(part.getMax()));
        min.setText(String.valueOf(part.getMin()));

    }
    public void onMax(KeyEvent keyEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        alert.setHeaderText("Max and Min Values");
        alert.setContentText("The Min value must be less than the Max and the Inventory level must be between those Values");

        alert.showAndWait();
    }
    // add button that populate data from the top to bottom table
    public void onAdd(ActionEvent actionEvent) throws Exception {
        Part part = part_Tableview1.getSelectionModel().getSelectedItem();
        associatePart.add(part);
        aPart_Tableview.setItems(associatePart);
    }
    // delete highlighted data in the bottom table
    public void deleteAssociatedPart(ActionEvent actionEvent) throws Exception {
        if (aPart_Tableview.getSelectionModel().isEmpty()) {
            //deleteAssociatedPart(Product part)
            System.out.println("Please choose a product from above");
            return;
        } else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert");

            alert.setHeaderText("ALERT");
            alert.setContentText("ARE SURE YOU WANT TO DELETE THIS SELECTED VALUE!!");

            alert.showAndWait();

        }
        {
            int Product = aPart_Tableview.getSelectionModel().getSelectedIndex();
            aPart_Tableview.getItems().remove(Product);
        }

    }
    // save highlighted table data
    public void onSave(ActionEvent actionEvent) throws Exception {
        if(name.getText().equals("")){
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");

                alert.setHeaderText("ERROR!!");
                alert.setContentText("ALL TEXT FIELDS MUST BE FILL IN AND HAVE THE CORRECT DATA TYPE BEFORE YOU CAN SAVE THIS VALUE");

                alert.showAndWait();

            }
        }

        Product product = new Product(autoId,
                name.getText(), Double.parseDouble(price.getText()), Integer.parseInt(stock.getText()),
                Integer.parseInt(min.getText()), Integer.parseInt(max.getText()));

        for(Product p: associateProducts){
            product.addAssociatedProduct(p);
        }

        Inventory.addProduct(product);
        System.out.println("Product was added!!");
        autoId++;


}

    // cancel running application, return to the main form
    public void onCancel(ActionEvent actionEvent) throws Exception {
        Parent addProduct = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene scene = new Scene(addProduct);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}