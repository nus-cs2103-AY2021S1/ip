import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.util.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

//TODO: [W3] Stretch Goals: Level 8- Use date related command

/**
 * Driver of {@code Duke} programme.
 */
public class Duke {

    private static final String DATA_FILE = "data/duke.txt";
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Initialises the Duke programme and load tasks from default file.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(DATA_FILE);
        try {
            taskList = new TaskList(storage.load());
            ui.showLoadSuccess(taskList);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Executes the {@code Duke} program using the input provided
     *
     * @param input Command to run.
     */
    public void run(String input) {
        if (Command.isTerminated) {
            return;
        }

        try {
            Command c = Parser.parse(input);
            c.execute(taskList, ui);

            if (c instanceof ExitCommand) {
                terminate();
            }

        } catch (DukeException de) {
            ui.printError(de);
        }
    }

    /**
     * Performs saving and clean up on programme termination.
     */
    public void terminate() {
        storage.saveToFile(taskList.export());
        ui.showExit();
    }

    /**
     * Retrieves the output buffer to display in UI.
     *
     * @return output string.
     */
    public String getUiUpdate() {
        return ui.flush();
    }

    /**
     * Retrieves the error buffer (if any) to display in UI
     *
     * @return Error message. If no error is found, empty string is returned
     */
    public String getErrorString() {
        return ui.flushError();
    }
}
