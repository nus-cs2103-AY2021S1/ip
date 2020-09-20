package duke;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.task.TaskList;

import javafx.application.Platform;

/**
 * Initialises a Duke object that contains a <code>taskList</code> and <code>storage</code> object.
 * Each run of this app should only use one Duke object.
 *
 * @author Hui Ling
 * @version 2.0
 * @see Storage
 * @see TaskList
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;

    /**
     * Constructor that creates a <code>Duke</code> object with a <code>Storage</code> object
     * using the file path "data/tasks.txt" relative to working directory,
     * and a <code>taskList</code> loaded by <code>storage</code>.
     */
    public Duke() {
        storage = new Storage("data/tasks.txt");
        try {
            taskList = storage.load();
        } catch (Exception e) {
            Ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Generates a response to user input by parsing the input into a <code>Command</code> and executing it.
     *
     * @param input  user text input
     * @return       a String of the Duke's response
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                closeApp();
            }
            return c.execute(taskList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private void closeApp() {
        // exits the JavaFX app after 1 second, so that user can see the goodbye message first
        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
            Platform.exit();
        });
    }
}
