import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        testData();
        launch(args);
        System.out.println("Hello world!");
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/mainForm.fxml")));
        stage.setTitle("");
        stage.setScene(new Scene(root,800, 600));
        stage.show();
    }
    public static void testData(){
        Part p1 = new InHouse(1, "door", 500.00, 20, 5, 25, 100);
        Inventory.addPart(p1);
        Part p2 = new InHouse(2, "handle", 300.00, 15, 5, 25, 150);
        Inventory.addPart(p2);
        Part p3 = new InHouse(3, "window", 150.00, 10, 5, 25, 110);
        Inventory.addPart(p3);
        Part p4 = new OutSourced(3, "Tinted window", 200.00, 10, 5, 25, "Tech");
        Inventory.addPart(p4);
        Part p5 = new OutSourced(3, "custom door", 750.00, 10, 5, 25, "Tech");
        Inventory.addPart(p5);
        Product pr1 = new Product(1, "Bentley", 200000.00, 20, 5, 25);
        Inventory.addProduct(pr1);
        Product pr2 = new Product(2, "Range Rover", 150000.00, 15, 5, 25);
        Inventory.addProduct(pr2);
        Product pr3 = new Product(3, "BMW X 5", 75000.00, 10, 5, 15);
        Inventory.addProduct(pr3);
    }

}