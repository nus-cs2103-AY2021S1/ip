import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private Stage window;

    @Override
    public void start(Stage stage) {
        try {
            window = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            window.setScene(scene);
            window.setTitle("Duke");
            window.getIcons().add(new Image(Main.class.getResourceAsStream("/images/userIcon.png")));
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().setWindow(window);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        window.close();
    }
}
