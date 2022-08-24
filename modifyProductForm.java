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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** A class for  modify Product form. */
public class modifyProductForm implements Initializable {

    //static int autoId = 100;
   // UUID autoId = UUID.randomUUID();
    @FXML
    private TableView<Part> partView;
    @FXML
    public TableColumn<Part, Integer> partID;
    @FXML
    private TableColumn<Part, String> partName;
    @FXML
    private TableColumn<Part, Integer> inventoryLevel;
    @FXML
    private TableColumn<Part, Double> priceCost;
    @FXML
    private TableView<Part> aModifiedV;
    @FXML
    public TableColumn<Part, Integer> aPartID;
    @FXML
    private TableColumn<Part, String> aPartName;
    @FXML
    private TableColumn<Part, Integer> aInventory;
    @FXML
    private TableColumn<Part, Double> aPriceCost;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField stock;
    @FXML
    private TextField priceText;
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
    private Button cancel;
    /** associatePart collections. @param associatePart. */
    ObservableList<Part> associatePart = FXCollections.observableArrayList();
    /** The first table view and the table columns. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partView.setItems(Inventory.getAllParts());
        partID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partName.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));

        /** The second table view and the table columns. */
        aPartID.setCellValueFactory(new PropertyValueFactory<>("id"));
        aPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        aPriceCost.setCellValueFactory(new PropertyValueFactory<>("price"));
        aInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));


    }
    /** populate text fields. */
    public void sendProduct(Product product) {
        id.setText(String.valueOf(product.getId()));
        name.setText(String.valueOf(product.getName()));
        stock.setText(String.valueOf(product.getStock()));
        priceText.setText(String.valueOf(product.getPrice()));
        max.setText(String.valueOf(product.getMax()));
        min.setText(String.valueOf(product.getMin()));
        aModifiedV.setItems(product.getAllAssociatedParts());
    }
    /** create an alert when you click and key in a value. */
    public void onMax(KeyEvent keyEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        alert.setHeaderText("Max and Min Values");
        alert.setContentText("The Min value must be less than the Max and the Inventory level must be between those Values");

        alert.showAndWait();
    }
    /** add button that populate table data from the top table view to bottom table view.
     * LOGICAL ERROR was using the wrong table data */
    @FXML
    public void onAdd(ActionEvent actionEvent) throws Exception {
        Part part = partView.getSelectionModel().getSelectedItem();
        associatePart.add(part);
        aModifiedV.setItems(associatePart);
    }
    /** delete selected item from the table. */
    @FXML
    public void onDelete(ActionEvent actionEvent) throws IOException {
        if (aModifiedV.getSelectionModel().isEmpty()) {
            System.out.println("Please choose a part from above");
            return;
        } else System.out.println("Would you like to delete this PART!");
        {
            int part = aModifiedV.getSelectionModel().getSelectedIndex();
            aModifiedV.getItems().remove(part);
        }
    }
    /** same items to the table. */
    @FXML
    public void onSave(ActionEvent actionEvent) throws Exception {
        try {
            Product product = new Product(Integer.parseInt(id.getText()),
                    name.getText(), Double.parseDouble(priceText.getText()), Integer.parseInt(stock.getText()),
                    Integer.parseInt(min.getText()), Integer.parseInt(max.getText()));

            for (Part p : associatePart) {
                product.addAssociatedPart(p);
            }

            Inventory.addProduct(product);
            System.out.println("Product was added!!");



        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning alert");

            alert.setHeaderText("INCORRECT DATA TYPE");
            alert.setContentText("All text fields must have the correct data type");

            alert.showAndWait();
        }
    }
    /** cancel current application and return to the main form. */
    @FXML
    public void onCancel(ActionEvent actionEvent) throws Exception {
        Parent addParts = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene scene = new Scene(addParts);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    /** remove selected item from the table. */
    @FXML
    public void onRemovePart(ActionEvent actionEvent) throws Exception {

    }
}
