package duke;

import java.io.File;
import java.io.IOException;

import duke.common.Ui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static MainWindow mainWindow;
    private final Duke duke = new Duke(System.getProperty("user.dir") + File.separator + "data"
            + File.separator + "duke" + ".txt");
    private final Ui ui = new Ui();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            mainWindow = fxmlLoader.getController();
            mainWindow.printUserDialog(
                    ui.showLine() + "\n" + ui.showWelcome() + "\n" + ui.getMenu() + "\n" + ui.showLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MainWindow getMainWindow() {
        return mainWindow;
    }
}
