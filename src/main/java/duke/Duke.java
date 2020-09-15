package duke;

import duke.commands.Command;
import duke.tasks.TaskList;
import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.scene.control.Label;

/**
 * Duke program is a Task Management App. It takes in user command for task manipulations
 * and shows a list of Tasks that is ongoing.
 */
public class Duke extends Application {
    private String filePath;
    private Storage storage;
    public Ui ui;
    private TaskList taskList;

    /**
     * Constructor.
     * @param filePath string path of the file for storage purpose.
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            taskList = storage.init();
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Run start the program.
     * Initialise the relevant objects to handle user commands.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String inputLine = ui.readNextLine();
                Command command = Parser.parse(inputLine);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();

            } catch (DukeException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Returns a response to the user's input.
     *
     * @param input User input.
     * @return String response from program.
     */
    public String getResponse(String input) {

        String output = "";

        try {
            Command c = Parser.parse(input);
            output += c.execute(taskList, ui, storage);
        } catch (DukeException | IOException e) {
            output += ui.showError(e.getMessage());
        }

        return output;
    }

    /**
     * Main program to run the application.
     * @param args
     */
    public static void main(String[] args) {
        String filePath = "data/duke.txt";
        Duke duke = new Duke(filePath);
        duke.run();
    }
}
