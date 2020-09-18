import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**@@author SE-EDU student project guide, a sub-project of the se-education.org.
 * Point of contact: Damith C. Rajapakse https://www.comp.nus.edu.sg/~damithch/
 *
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private Duke duke = new Duke();

    /**@@author SE-EDU student project guide, a sub-project of the se-education.org.
     * Point of contact: Damith C. Rajapakse https://www.comp.nus.edu.sg/~damithch/
     *
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
