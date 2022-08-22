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


public class modifyPartForm implements Initializable {
    // buttons for the modify part table view
    @FXML Button save;
    @FXML Button cancel;
    @FXML
    private RadioButton inHouseRadio;
    @FXML
    private RadioButton outSourcedRadio;
    @FXML
    private ToggleGroup radiobutton;
    // test fields for the modify table view
    @FXML private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField stock;
    @FXML
    private TextField priceCost;
    @FXML
    private TextField max;
    @FXML
    private TextField min;
    @FXML
    private TextField machineID;
    @FXML
    private Label label;
    private TableView<Part> modifyPartTableview;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void onInHouse(ActionEvent actionEvent) {
        if (inHouseRadio.isSelected()) {
            this.label.setText("Machine ID");


        }
        }
        public void onOutSourced (ActionEvent actionEvent){
            if (outSourcedRadio.isSelected()) {
                this.label.setText("Company Name");
            }
        }
    // to populate the text fields of the form
    public void sendPart(Part part){
    id.setText(String.valueOf(part.getId()));
    name.setText(String.valueOf(part.getName()));
    stock.setText(String.valueOf(part.getStock()));
    priceCost.setText(String.valueOf(part.getPrice()));
    max.setText(String.valueOf(part.getMax()));
    min.setText(String.valueOf(part.getMin()));
    machineID.setText(String.valueOf(InHouse.getMachineID()));


    }
    public void onMax(KeyEvent keyEvent) throws Exception {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning alert");

        alert.setHeaderText("Max and Min Values");
        alert.setContentText("The Min value must be less than the Max and the Inventory level must be between those Values");

        alert.showAndWait();
    }
    @FXML // save button if in-house or out-sourced button is selected
    public void onSave(ActionEvent actionEvent) throws Exception {

        if (inHouseRadio.isSelected()) {
            Part inHouse = new InHouse(Integer.parseInt(id.getText()),
                    name.getText(), Double.parseDouble(priceCost.getText()), Integer.parseInt(stock.getText()),
                    Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), Integer.parseInt(machineID.getText()));

            Inventory.addPart(inHouse);
            System.out.println("Part was Modified!!");
        }
        if (outSourcedRadio.isSelected()) {
            Part outSourced = new OutSourced(Integer.parseInt(id.getText()),
                    name.getText(), Double.parseDouble(priceCost.getText()), Integer.parseInt(stock.getText()),
                    Integer.parseInt(min.getText()), Integer.parseInt(max.getText()), machineID.getText());

            Inventory.addPart(outSourced);
            System.out.println("Part was Modified!!");

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

