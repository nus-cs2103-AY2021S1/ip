package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main class, responsible for running the program.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private boolean isRunning;

    /**
     * Creates a <code>Duke</code> object.
     * @param filePath The path of the data file that this object interacts with
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        isRunning = true;
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showFileLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Generate the response for the GUI
     * @param fullCommand The input command of the user
     * @return A response of the program
     */
    public String getResponse(String fullCommand) {
        String response;
        try {
            Command command = Parser.parse(fullCommand);
            if (command.isStart()) {
                isRunning = true;
            }
            if (!isRunning) {
                return "I'm sleeping...zzz";
            }
            if (command.isExit()) {
                isRunning = false;
            }
            response = command.execute(tasks, ui, storage);
            storage.write(tasks);
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
        }
        return response;
    }
}
