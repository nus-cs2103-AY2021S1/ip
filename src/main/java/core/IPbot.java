package core;

import command.Command;
import command.CommandHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.MainWindow;

import java.io.IOException;
import java.net.URL;
import java.util.stream.Stream;

/**
 * Contains the iPbot GUI logic.
 */
public class IPbot extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = IPbot.class.getResource("/view/MainWindow.fxml");
        BorderPane root =  FXMLLoader.load(url);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
