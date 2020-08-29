package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Encapsulates the greenfield project Duke that manages user tasks.
 */
public class Duke extends Application {
    private Ui ui;
    private Parser parser;
    private TaskManager taskManager;

    /**
     * Constructs a duke object.
     */
    public Duke() {
        this.ui = new Ui();
        this.taskManager = new TaskManager();
    }

    /**
     * Initialises the program by using ui object to display the welcome message,
     * and calls on taskManager object to begin requesting and processing user input.
     */
    public void run() {
        this.ui.printWelcomeMessage();
        this.taskManager.manage();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Drives program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
