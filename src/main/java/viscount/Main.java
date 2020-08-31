package viscount;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import viscount.gui.MainWindow;

public class Main extends Application {
    /**
     * Launches Viscount.
     *
     * @param args Standard arguments.
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        AnchorPane anchorPane = new MainWindow();
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }
}
