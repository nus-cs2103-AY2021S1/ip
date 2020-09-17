package bob;

import java.io.IOException;

import bob.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    public Main() {

    }

    private Bob bob = new Bob();

    @Override
    public void start(Stage stage) {
        AnchorPane anchorPane = new MainWindow();
        Scene scene = new Scene(anchorPane);
        stage.setTitle("Bob, the personal assistant");
        stage.setScene(scene);
        stage.show();
    }
}
