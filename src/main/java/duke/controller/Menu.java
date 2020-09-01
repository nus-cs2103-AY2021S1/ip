package duke.controller;

import java.io.IOException;

import duke.ui.Messenger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Menu {
    @FXML
    private VBox dukeLogo;

    @FXML
    private Button startButton;

    @FXML
    public void initialize() {
        Label labelMessage = new Label(Messenger.LOGO);
        labelMessage.setFont(Font.font("Vendera", 40));
        dukeLogo.getChildren().add(labelMessage);
        dukeLogo.setAlignment(Pos.CENTER);
    }

    @FXML
    public void switchToMain() throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) startButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/view/MainWindow.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
