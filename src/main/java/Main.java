import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            MainWindow mainWindow = new MainWindow();
            fxmlLoader.setController(mainWindow);
            fxmlLoader.setRoot(mainWindow);
            fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            scene.getStylesheets().add("view/styles.css");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle(" Mr. Meeseeks");
            stage.getIcons().add(new Image("images/MeeseeksBox.png"));
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
