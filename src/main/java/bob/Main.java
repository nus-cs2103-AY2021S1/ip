package bob;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    /**
     * An empty constructor.
     */
    public Main() {

    }

    /**
     * Initialises the Graphical User Interface of Bob.
     * @param stage the stage of the GUI.
     */
    @Override
    public void start(Stage stage) {
        //@@author sc-arecrow
        //Adapted from https://github.com/sc-arecrow/ip/blame/master/src/main/java/viscount/Main.java
        AnchorPane anchorPane = new MainWindow();
        Scene scene = new Scene(anchorPane);
        stage.setTitle("Bob, the personal assistant");
        stage.setScene(scene);
        stage.show();
    }
}
