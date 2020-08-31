package duke.duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import duke.command.DukeException;
import duke.command.Parser;

import duke.storage.Storage;

import duke.task.TaskList;

/**
 * Duke class that runs the Duke chat bot program.
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList list;
    private Ui ui;
    private Parser parser;

    public Duke() {
        this.storage = new Storage();
        this.list = storage.getList();
        this.ui = new Ui();
        this.parser = new Parser(this.list);
    }

    /**
     * Begin the Duke chat bot program.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                ui.showOutput(parser.processCommand(fullCommand));
                isExit = parser.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        storage.updateFile(list);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}


