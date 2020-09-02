package rogue.ui;

import rogue.Rogue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The interface of {@code Rogue} with which the user interacts.
 * Reads user input from the console, and displays messages,
 * errors, and other textual information onto the console.
 */
public class Ui {
    public static void init(Stage stage, Rogue rogue) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(rogue.Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRogue(rogue);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
