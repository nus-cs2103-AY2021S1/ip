package core;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Contains the iPbot GUI logic.
 */
public class IpBot extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = IpBot.class.getResource("/view/MainWindow.fxml");
        BorderPane root = FXMLLoader.load(url);

        primaryStage.setTitle("iPbot");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
