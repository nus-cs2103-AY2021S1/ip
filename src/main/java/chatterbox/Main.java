package chatterbox;

import java.io.IOException;

import chatterbox.ui.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Loads the FXML files and sets the scene.
 */
public class Main extends Application {
    private final Chatterbox chatterbox = new Chatterbox();

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param stage The primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            Image icon = new Image(Main.class.getResourceAsStream("/images/Icon.jpg"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            MainWindow mw = new MainWindow();
            fxmlLoader.setController(mw);
            fxmlLoader.setRoot(mw);
            fxmlLoader.load();
            Scene scene = new Scene(mw);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Chatterbox");
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setChatterbox(chatterbox);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
