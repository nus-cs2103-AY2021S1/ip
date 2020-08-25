package duke;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.parsers.Parser;
import duke.tasks.Task;
import duke.exceptions.DukeException;
import duke.commands.Command;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/** Represents the main class. */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /** Constructor.
     *
     * @param directoryPath The path of the dataFile's directory.
     * @param dataFilePath The path of the dataFile.
     */
    public Duke(String directoryPath, String dataFilePath) {
        ui = new Ui();
        storage = new Storage(directoryPath, dataFilePath);
        ArrayList<String> lines = storage.readFile();
        parser = new Parser();
        ArrayList<Task> tasks = parser.parseSavedTaskList(lines);
        taskList = new TaskList(tasks);
    }

    /** Runs the Duke program. */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        try {
            while (!isExit) {
                String userInput = ui.readCommand();
                Command c = parser.parse(userInput, taskList.tasks);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            }
        } catch (DukeException | DateTimeParseException e) {
            ui.showError(e);
        }
    }

    /** The main method which runs the Duke program. */
    public static void main(String[] args) {
        new Duke("src/main/data", "src/main/data/data.txt").run();
    }
}
