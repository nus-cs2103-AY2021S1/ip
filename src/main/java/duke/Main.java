package duke;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;

/**
 * This class is the main entry point into the Duke application.
 * Solution below adapted from https://github.com/sc-arecrow/viscount/tree/master
 *
 * @author sc-arecrow
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Starts the GUI of Duke chatbot.
     *
     * @param stage Stage shown on the UI.
     */
//    @Override
//    public void start(Stage stage) {
//        AnchorPane ap = new MainWindow();
//        Scene scene = new Scene(ap);
//        stage.setScene(scene);
//        stage.show();
//    }
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        Label helloWorld2 = new Label("Hello World 2.0!"); // Creating a new Label control
        Scene scene2 = new Scene(helloWorld2); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        Stage stage2 = new Stage();
        stage2.setScene(scene2); // Setting the stage to show our screen
        stage2.show(); // Render the stage.
    }
}