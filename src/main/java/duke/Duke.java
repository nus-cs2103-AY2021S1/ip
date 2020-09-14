package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.util.Pair;

/**
 * Main class, responsible for running the program.
 */
public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private boolean isRunning;

    /**
     * The type of the response message
     */
    public enum ResponseType {
        MESSAGE,
        ERROR
    }

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
    public Pair<String, ResponseType> getResponse(String fullCommand) {
        String response;
        try {
            Command command = Parser.parse(fullCommand);
            if (command.isStart()) {
                isRunning = true;
            }
            if (!isRunning) {
                return new Pair<>("I'm sleeping...zzz", ResponseType.MESSAGE);
            }
            if (command.isExit()) {
                isRunning = false;
            }
            response = command.execute(tasks, ui, storage);
            return new Pair<>(response, ResponseType.MESSAGE);
        } catch (DukeException e) {
            response = ui.showError(e.getMessage());
            return new Pair<>(response, ResponseType.ERROR);
        }
    }

    /**
     * Store data into the data file
     * Called when the program is closed
     */
    public void cleanUp() {
        try {
            storage.write(tasks);
        } catch (DukeException e) {
            System.out.println("Cannot write file");
        }
    }
}
