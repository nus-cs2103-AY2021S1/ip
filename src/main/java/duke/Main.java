package duke;

import controller.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    /**
     * Initializes the main window and displays it.
     *
     * @param stage The stage of the javaFX application.
     */
    @Override
    public void start(Stage stage) {
        MainWindow mw = new MainWindow();
        Scene scene = new Scene(mw);
        stage.setTitle("Serina");
        stage.setScene(scene);
        mw.setDuke(duke);
        mw.initialize();
        stage.show();
    }
}
