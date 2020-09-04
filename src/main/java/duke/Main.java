package duke;

import duke.response.Response;
import duke.view.MainWindow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
    private final Duke duke;

    /**
     * The main GUI class.
     */
    public Main() {
        this.duke = new Duke("data/tasks.txt");
        Response initResponse = this.duke.getInitResponse();

        if (initResponse.isError()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, initResponse.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void start(Stage stage) {
        AnchorPane mainWindow = new MainWindow(this.duke);
        Scene scene = new Scene(mainWindow);

        scene.getStylesheets().add("/stylesheets/base.css");
        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/crown-solid.png")));
        stage.show();
    }
}
