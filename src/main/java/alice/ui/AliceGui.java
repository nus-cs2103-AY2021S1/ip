package alice.ui;

import java.io.IOException;

import alice.Alice;
import alice.ui.controller.MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Represents the GUI manager for Alice.
 */
public class AliceGui extends Application {
    private Alice alice;
    private MainWindow mainWindow;

    @Override
    public void init() throws Exception {
        super.init();

        alice = new Alice();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            mainWindow = new MainWindow(primaryStage, alice);
            mainWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
