package duke.gui;

import duke.Duke;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Gui extends Application {

    private Duke duke = new Duke("data/gui-test.txt");

    @Override
    public void start(Stage stage) {
        AnchorPane ap = new MainWindow(duke);
        Scene scene = new Scene(ap);
        stage.setTitle("Meimei Bot");
        stage.setScene(scene);
        stage.show();
    }
}
