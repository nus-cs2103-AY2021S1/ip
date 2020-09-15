import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke = new Duke();

    /**
     * Creates a GUI stage for user to use.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("DUKE_ip_v2.0");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            String reminders = duke.getReminder();
            fxmlLoader.<MainWindow>getController().showReminder(reminders);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
