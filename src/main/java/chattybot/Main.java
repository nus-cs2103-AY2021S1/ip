package chattybot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author Jeffry Lum
//Guides for SE Student Project: Java FX Tutorial
/**
 * A GUI for ChattyBot using FXML.
 */
public class Main extends Application {

    private ChattyBot chattybot = new ChattyBot();

    /**
     * Starts up the application layout and waits for user's commands.
     *
     * @param stage Main layout of the applcation.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setChattyBot(chattybot);
            stage.setTitle("Chatty-Bot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
