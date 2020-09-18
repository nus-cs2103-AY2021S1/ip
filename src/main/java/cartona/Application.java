package cartona;

import java.io.IOException;

import cartona.ui.MainWindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Cartona using FXML.
 *
 * @author Jaya Rengam
 */
public class Application extends javafx.application.Application {

    private Cartona cartona = new Cartona();

    @Override
    public void start(Stage stage) {
        cartona.load();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCartona(cartona);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Application.launch(Application.class, args);
    }
}
