import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sparrow.Sparrow;

public class Main extends Application {

    private final Sparrow sparrow = new Sparrow();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Sparrow");
            fxmlLoader.<MainWindow>getController().setSparrow(sparrow);
            fxmlLoader.<MainWindow>getController().welcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
