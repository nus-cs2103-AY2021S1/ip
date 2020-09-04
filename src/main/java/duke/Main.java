package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = new AnchorPane();
            fxmlLoader.setRoot(ap);
            fxmlLoader.load();

            Scene scene = new Scene(ap);
            scene.getStylesheets().add(this.getClass().getClassLoader().getResource("style/main_window.css").toExternalForm());
            stage.setScene(scene);
            this.duke = new Duke(stage);
            fxmlLoader.<MainWindow>getController().initialize(duke);
            stage.setTitle("Elon Musk");
            stage.getIcons().add(new Image(this.getClass().getClassLoader().getResource("images/icon.jpg").toString()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
