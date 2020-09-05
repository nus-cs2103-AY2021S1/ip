import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nekochan.ui.MainWindow;

/**
 * A GUI for NekoChan using FXML.
 */
public class Main extends Application {


    private static final String STAGE_TITLE = "Neko-chan";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(STAGE_TITLE);
            fxmlLoader.<MainWindow>getController().start();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
