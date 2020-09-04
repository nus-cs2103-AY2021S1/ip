package duke;

import java.io.IOException;

import duke.io.OutputHandlerForGui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DukeGui extends Application {

    private Duke duke;

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DukeGui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            this.duke = new Duke(new OutputHandlerForGui(fxmlLoader.<MainWindow>getController()));
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDukeGui(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleUserInput(String userInput) {
        this.duke.processOneCommand(userInput);
    }


}
