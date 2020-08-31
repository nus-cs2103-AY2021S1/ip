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
     * Initializes the duke controller.
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

    // TODO enable greetings!

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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
