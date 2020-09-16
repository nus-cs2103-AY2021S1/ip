package sparkles.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sparkles.Sparkles;

/**
 * A GUI for Sparkles using FXML.
 */
public class Main extends Application {

    private Sparkles sparkles = new Sparkles("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("                                     Sparkles");

            String welcomeMsg = "*Hello, I am Sparkles*\n\n     How can I help you?";
            Image sparklesImage = new Image(this.getClass().getResourceAsStream("/images/Sparkles-transparent.png"));
            DialogBox welcome = DialogBox.getSparklesDialog(welcomeMsg, sparklesImage);

            fxmlLoader.<MainWindow>getController().setDialogContainer(welcome);
            fxmlLoader.<MainWindow>getController().setSparkles(sparkles);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
