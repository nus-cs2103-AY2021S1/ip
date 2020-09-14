package duke;

import static duke.util.Keyword.TIMEOUT_DURATION;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Main class to run Duke program.
 */
public class Duke {

    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    /**
     * Initializes the duke backend.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.getTasks());
    }

    /**
     * Obtains input from user and outputs accordingly.
     *
     * @param input User input.
     * @return Duke response message.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            checkForExit(command);
            return command.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Method to run a method after a 1 second delay.
     *
     * @param runnable Method to run.
     */
    private void setTimeout(Runnable runnable) {
        Runnable program = () -> runAfterDelay(runnable);
        new Thread(program).start();
    }

    /**
     * Runs the {@code Runnable} object after a delay of 600ms.
     *
     * @param runnable Method to run.
     */
    private void runAfterDelay(Runnable runnable) {
        try {
            Thread.sleep(TIMEOUT_DURATION);
            runnable.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the command is an {@code ExitCommand}, quitting the program if so.
     *
     * @param command Input Command from user.
     */
    private void checkForExit(Command command) {
        if (command.isExit()) {
            setTimeout(Platform::exit);
        }
    }
}
