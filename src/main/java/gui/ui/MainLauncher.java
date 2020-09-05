package gui.ui;

import java.io.IOException;

import duke.ChatbotApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A launcher class to workaround classpath issues.
 */
public class MainLauncher extends Application {
    
    private final ChatbotApplication chatbotApplication = new ChatbotApplication("##" , System.getProperty("user.dir"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainLauncher.class.getResource("/view/TestWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            stage.setTitle("Duke");

            fxmlLoader.<TestWindow>getController().setChatbot(chatbotApplication);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}