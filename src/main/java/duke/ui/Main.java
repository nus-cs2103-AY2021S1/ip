package duke.ui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private final String filePath = System.getProperty("user.dir")
            + (System.getProperty("user.dir").endsWith("text-ui-test")
            ? "/test_data/duke.txt"
            : "/data/duke.txt");
    private Duke duke = new Duke(filePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets()
                    .add(this.getClass().getClassLoader().getResource("style/MainWindow.css").toString());
            stage.setScene(scene);
            stage.setTitle("Duke");
            stage.getIcons().add(
                    new Image(this.getClass().getClassLoader().getResource("images/crm.png").toString())
            );
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        duke.cleanUp();
    }
}
