package shiro;

import java.io.IOException;

import shiro.ui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Shiro using FXML.
 */
public class Main extends Application {

    private Shiro shiro = new Shiro(Shiro.FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setTitle("shiro");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setShiro(shiro);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
