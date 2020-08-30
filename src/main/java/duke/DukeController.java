package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Manages the duke program.
 */
public class DukeController {

    private final Ui ui;
    private final Storage storage;
    private final TaskList tasks;

    /**
     * Initializes duke controller.
     */
    public DukeController() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.getTasks());
    }

    /**
     * Runs the duke program.
     */
    public void run() {
        ui.greetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printMsg(e.getMessage());
            }
        }
    }
}
