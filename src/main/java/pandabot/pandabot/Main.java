package pandabot.pandabot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for PandaBot using FXML.
 */
public class Main extends Application {

    private PandaBot pandaBot = new PandaBot();

    /**
     * Starts the GUI application for PandaBot.
     *
     * @param stage the stage for this application, onto which the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPandaBot(pandaBot);
            stage.setTitle("PandaBot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
