package botbot;

import java.io.IOException;

import botbot.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Botbot using FXML.
 *
 * @author wakululuu-reused.
 * Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications.
 */
public class Main extends Application {
    private static final String APPLICATION_NAME = "botbot";
    private static Stage mainStage;
    private final Botbot botbot = new Botbot("data/botbot.txt");

    private void setStage(Stage stage) {
        mainStage = stage;
    }

    /**
     * Closes the main stage of Botbot.
     */
    public static void closeStage() {
        assert mainStage.isShowing() : "Attempt to close main stage that is not showing";
        mainStage.close();
    }

    @Override
    public void start(Stage stage) {
        try {
            setStage(stage);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBotbot(botbot);
            stage.setTitle(APPLICATION_NAME);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            assert false;
        }
    }
}
