package duke;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.task.TaskList;

import javafx.application.Platform;

/**
 * Initialises a Duke object that contains a <code>taskList</code>, <code>storage</code> and <code>ui</code> object.
 * Each run of this app should only use one Duke object.
 *
 * @author Hui Ling
 * @version 2.0
 * @see Storage
 * @see TaskList
 * @see Ui
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructor that creates a <code>Duke</code> object with an <code>ui</code> object,
     * a <code>storage</code> object at the file path "data/tasks.txt" relative to working directory,
     * and a <code>taskList</code> loaded by <code>storage</code>.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            taskList = storage.load();
        } catch (Exception e) {
            ui.showLoadingError();
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
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private void closeApp() {
        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
            Platform.exit();
        });
    }
}
