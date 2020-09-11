package viscount;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import viscount.gui.MainWindow;

/**
 * Represents the main entry point into the application.
 */
public class Main extends Application {
    /**
     * Starts the GUI of Viscount.
     *
     * @param stage Stage shown.
     */
    @Override
    public void start(Stage stage) {
        AnchorPane anchorPane = new MainWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("Viscount Task Manager");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/applicationIcon.png")));
        stage.show();
    }
}
