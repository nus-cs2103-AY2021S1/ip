package duke;

import duke.command.Command;
import duke.exception.DukeException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Entry point of Duke chatbot.
 * Initializes the chatbot and starts interaction with user.
 */
public class Duke extends Application {

    private static final String DATA_PATHNAME = "data/duke.txt";
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new instance of a Duke object.
     */
    public Duke() {
        storage = new Storage(DATA_PATHNAME);
        ui = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the program until termination.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Runs the Duke Chatbot.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello Duke!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
