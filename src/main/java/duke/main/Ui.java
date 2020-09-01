package duke.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Ui (User Interface) is used to interact with the user. It provides
 * function that will form the interface that the user will see.
 */
public class Ui extends Application {

    @Override
    public void start(Stage stage) {
        // Create new Label control
        Label firstGui = new Label("First GUI for Popi!");
        // Set label to the scene
        Scene scene = new Scene(firstGui);

        // Set the stage
        stage.setScene(scene);
        // Render the stage
        stage.show();
    }

    /**
     * Displays a horizontal line.
     */
    public void showLine() {
        System.out.println("_".repeat(55));
    }

    /**
     * Displays the greeting message to the user.
     */
    public void showGreetingMessage() {
        System.out.println(" Hey there! I am Popi" + "\n"
                + " How can I help you?");
    }
}
