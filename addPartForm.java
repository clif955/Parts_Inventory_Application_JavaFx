package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.*;

import java.net.URL;
import java.util.ResourceBundle;


public class addPartForm implements Initializable {
    static int autoId = 1;
    @FXML private Label label;
    private TableView<Part> addPartTableview;
    // radio buttons for the part tableview
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outSourcedRadio;
    // Text fields for the add Part form
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
    private TextField machineID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    // method for populating text-fields on the part form
    public void sendPart(Product part) {
        id.setText(String.valueOf(part.getId()));
        name.setText(String.valueOf(part.getName()));
        stock.setText(String.valueOf(part.getStock()));
        price.setText(String.valueOf(part.getPrice()));
        max.setText(String.valueOf(part.getMax()));
        min.setText(String.valueOf(part.getMin()));

    }
    // alert for the max text field to prompt user to make sure inventory is between max and min
    public void onMax(KeyEvent keyEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        alert.setHeaderText("Max and Min Values");
        alert.setContentText("The Min value must be less than the Max and the Inventory level must be between those Values");

        alert.showAndWait();
    }

    @FXML // in-house radio  button
    public void onInHouse(ActionEvent actionEvent) {
        if (inHouseRadio.isSelected()) {
            this.label.setText("Machine ID");


        }
    }
    @FXML // out-sourced radio button
    public void onOutSourced(ActionEvent actionEvent) {
        if (outSourcedRadio.isSelected()) {
            this.label.setText("Company Name");
        }
    }
    @FXML // save button
    public void onSave(ActionEvent actionEvent) throws Exception {

         if (inHouseRadio.isSelected()) {
            Part inHouse = new InHouse(autoId,
                    name.getText(), Double.parseDouble(price.getText()), Integer.parseInt(stock.getText()),
                    Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), Integer.parseInt(machineID.getText()));

            Inventory.addPart(inHouse);
            System.out.println("Part was added!!");
        }
        if (outSourcedRadio.isSelected()) {
            Part outSourced = new OutSourced(autoId,
                    name.getText(), Double.parseDouble(price.getText()), Integer.parseInt(stock.getText()),
                    Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), machineID.getText());

            Inventory.addPart(outSourced);
            System.out.println("Part was added!!");
            autoId++;
        }
        Parent addParts = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene scene = new Scene(addParts);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
    @FXML // cancel button
    public void onCancel(ActionEvent actionEvent) throws Exception {
        Parent addParts = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene scene = new Scene(addParts);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
