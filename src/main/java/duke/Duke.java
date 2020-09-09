package duke;

import duke.command.Command;
import duke.common.DukeException;
import duke.common.Ui;
import duke.io.Storage;
import duke.io.TaskList;
import duke.parser.Parser;

/**
 * This is the start of the Duke application. There 3 main task that the application can record,
 * ToDo, Event and Deadline.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class Duke {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Class constructor that initialised the application.
     * Ui, Storage and TaskList are initialised in this constructor.
     *
     * @param filePath path of duke.txt, duke.txt acts as a simple database.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.taskList = new TaskList();
        }
    }

    /**
     * Start duke programme running.
     *
     * @param input String command
     */
    public void handleInput(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showLine();
        }
    }
}
