package duke;

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
    private final TaskList tasks;

    /**
     * Initializes the duke backend.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasks = new TaskList(storage.getTasks());
    }

    private void setTimeout(Runnable runnable) {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Obtains input from user and outputs accordingly.
     *
     * @param input User input.
     * @return Duke response message.
     */
    protected String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                setTimeout(() -> Platform.exit());
            }
            return c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
