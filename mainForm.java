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


public class mainForm implements Initializable {



        @FXML
        public TableColumn<Part, Integer> partID;
        @FXML
        private TableColumn<Part, String> partName;
        @FXML
        private TableColumn<Part, Integer> inventoryLevel;
        @FXML
        private TableColumn<Part, Double> price;
        @FXML
        private Button part_Add;
        @FXML
        private Button part_Modify;
        @FXML
        private Button part_Delete;
        @FXML
        private TextField partSearch;
        @FXML
        private TableView<Part> partView;

        @FXML
        private TableView<Part> modifyPartTableView;
        @FXML
        private TableView<Product> prod_Tableview;
        @FXML
        private TableView<Product> part_Tableview1;
        @FXML
        private TableView<Part> part_Tableview;
        @FXML
        private TableColumn<Product, Integer> prodID;
        @FXML
        private TableColumn<Product, String> prodName;
        @FXML
        private TableColumn<Product, Integer> prodInventory;
        @FXML
        private TableColumn<Product, Double> prodPrice;
        @FXML
        private Button prod_Add;
        @FXML
        private Button prod_Delete;
        @FXML
        private Button prod_Modify;
        @FXML
        private TextField prod_Search;
        @FXML
        private Button Exit;

        // set Table view columns
        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
        // for parts table
            part_Tableview.setItems(Inventory.getAllParts());
            partID.setCellValueFactory(new PropertyValueFactory<>("id"));
            partName.setCellValueFactory(new PropertyValueFactory<>("name"));
            price.setCellValueFactory(new PropertyValueFactory<>("price"));
            inventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));


        // for products table
            prod_Tableview.setItems(Inventory.getAllProducts());
            prodID.setCellValueFactory(new PropertyValueFactory<>("id"));
            prodName.setCellValueFactory(new PropertyValueFactory<>("name"));
            prodPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            prodInventory.setCellValueFactory(new PropertyValueFactory<>("stock"));

        }

    @FXML   // add button for main form for part
    public void onAdd(ActionEvent actionEvent) throws IOException {
        Parent addParts = FXMLLoader.load(getClass().getResource("/view/addPartForm.fxml"));
        Scene scene = new Scene(addParts);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @FXML  // modify button for the main form for part
    public void onModify(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modifyPartForm.fxml"));

        loader.load();

        modifyPartForm controller = loader.getController();
        controller.sendPart(part_Tableview.getSelectionModel().getSelectedItem());
        Parent root = loader.getRoot();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML  // delete button for the main form for part
    public void onDelete(ActionEvent actionEvent) throws IOException {
        if (part_Tableview.getSelectionModel().isEmpty()) {
                System.out.println("Please choose a part from above");
                return;
           } else
            {
                {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning alert");

                    alert.setHeaderText("ALERT");
                    alert.setContentText("ARE SURE YOU WANT TO DELETE THIS SELECTED VALUE!!");

                    alert.showAndWait();

                }
               int part = part_Tableview.getSelectionModel().getSelectedIndex();
                part_Tableview.getItems().remove(part);
           }
    }
    @FXML  // add button for the main form for product
    public void onProdAdd(ActionEvent actionEvent) throws IOException {
        Parent addParts = FXMLLoader.load(getClass().getResource("/view/addProductForm.fxml"));
        Scene scene = new Scene(addParts);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();


    }
    @FXML //  modify button for the main form for the product
    public void onProdModify(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modifyProductForm.fxml"));

        loader.load();

        modifyProductForm controller = loader.getController();
        controller.sendProduct(prod_Tableview.getSelectionModel().getSelectedItem());
        Parent root = loader.getRoot();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }
    @FXML // delete button for the main form for the product
    public void onProdDelete(ActionEvent actionEvent) throws IOException {

           if (prod_Tableview.getSelectionModel().isEmpty()) {
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
        int Product = prod_Tableview.getSelectionModel().getSelectedIndex();
        prod_Tableview.getItems().remove(Product);


    }
    @FXML // search text-field for the main form for part
    public void onSearch(KeyEvent keyEvent) throws IOException {
        try {

        Integer Id = Integer.valueOf(partSearch.getText());
            ObservableList<Part> items = FXCollections.observableArrayList();
            ObservableList<Part> tableview_List = Inventory.getAllParts();
            for (Part P : tableview_List) {
                if (P.getId() == Id )
                    items.add(P);
            }
            part_Tableview.setItems(items);
        }
        catch (NumberFormatException e){
                String partialName = partSearch.getText();
                ObservableList<Part> items = FXCollections.observableArrayList();
                ObservableList<Part> tableview_List = Inventory.getAllParts();
                for (Part P : tableview_List) {
                    if (P.getName().contains(partialName) )
                        items.add(P);
                }
                part_Tableview.setItems(items);
            }
    }
    @FXML // search text-field for the main form for product
    public void onProdSearch(KeyEvent keyEvent) throws IOException {
        try {
            Integer Id = Integer.valueOf(prod_Search.getText());
            ObservableList<Product> items2 = FXCollections.observableArrayList();
            ObservableList<Product> tableview_List2 = Inventory.getAllProducts();
            for (Product Pr : tableview_List2) {
                if (Pr.getId() == Id ) {
                    items2.add(Pr);
                }
            }
            prod_Tableview.setItems(items2);
        }
        catch(NumberFormatException e){
            String partialName2 = prod_Search.getText();
            ObservableList<Product> items2 = FXCollections.observableArrayList();
            ObservableList<Product> tableview_List2 = Inventory.getAllProducts();
            for (Product Pr : tableview_List2) {
                if (Pr.getName().contains(partialName2)) {
                    items2.add(Pr);
                }
            }
            prod_Tableview.setItems(items2);
        }

    }


    @FXML // exit button for the main form
    public void onExit(ActionEvent actionEvent) throws IOException {
        System.out.println("Exit Program");
        System.exit(0);
    }
}




