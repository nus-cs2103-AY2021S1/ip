package duke;

import java.io.IOException;

import command.Command;
import exception.DukeException;
import exception.NoSuchTaskException;

/**
 * Represents a <code>Duke</code> object which is a ChatBot that can keep track of several kinds of tasks.
 */
public class Duke {
    private final String DATA_DIRECTORY = "data";
    private final String SAVED_FILE_PATH = "data/duke.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage(DATA_DIRECTORY, SAVED_FILE_PATH);
        try {
            tasks = new TaskList(storage.loadSavedFile());
        } catch (IOException e) {
            ui.sayException(e);
        }
    }

    /**
     * Runs the <code>Duke</code> object.
     */
    public void run() {
        ui.hello();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.sayException(e);
            } finally {
                ui.showLine();
            }
        }
    }
}

