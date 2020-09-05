package junimo.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import junimo.Junimo;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Junimo junimo = new Junimo("./data/saved-tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Junimo");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(junimo);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void stop() throws Exception {
        junimo.save();
    }
}
