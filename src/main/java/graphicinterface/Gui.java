
package graphicinterface;

import java.io.IOException;

import duke.Duke;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A GUI for duke.Duke using FXML.
 */
public class Gui extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            Duke duke = new Duke("data/duke.txt");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().setTerminateFunction(() -> {
                //@@author John_D
                //Slight Modification from his solution on
                //https://stackoverflow.com/questions/27334455/how-to-close-a-stage-after-a-certain-amount-of-time-javafx.
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(event -> stage.close());
                delay.play();
            });
            fxmlLoader.<MainWindow>getController().welcomeMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}