import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Cait using FXML.
 */
public class Main extends Application {

    private Cait cait = new Cait();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Cait!");
            stage.setResizable(false);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCait(cait);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
