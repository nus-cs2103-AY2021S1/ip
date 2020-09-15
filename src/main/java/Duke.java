import java.io.FileNotFoundException;
import java.io.IOException;

import duke.DukeException;
import duke.Parser;
import duke.backend.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The main Duke class.
 */
public class Duke {
    private static final String FILEPATH = "data/tasks.txt";

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Initialises tasks and storage of Duke.
     */
    public Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage(FILEPATH);
            this.tasks = new TaskList(storage.loadFile());
        } catch (FileNotFoundException | DukeException e) {
            ui.sayErrorMessage(e);
            ui.informFileNotFound();
            this.tasks = new TaskList();
        }
    }

    private void run() {
        ui.sayGreetings();
        boolean isBye = false;
        while (!isBye) {
            String userInput = ui.receiveUserInput();
            try {
                Parser.parseInput(userInput, ui, tasks, storage);
            } catch (DukeException | IOException e) {
                ui.sayErrorMessage(e);
            } finally {
                isBye = Parser.isBye(userInput);
            }
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    // For implementation of JavaFX.
    public String getResponse(String input) {
        if (Parser.isBye(input)) {
            System.exit(0);
        }
        try {
            return Parser.parseInput(input, ui, tasks, storage);
        } catch (DukeException | IOException e) {
            return ui.sayErrorMessage(e);
        }
    }

    // For implementation of JavaFX.
    public String getGreetings() {
        return ui.sayGreetings();
    }
}
