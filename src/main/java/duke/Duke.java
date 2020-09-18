package duke;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.task.TaskList;

import javafx.application.Platform;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Hui Ling
 * @since 2020-09-18
 */

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

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
     * Generates Duke's response to user input
     * @param input user text input
     * @return Duke's response
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            if (c.isExit()) {
                closeApp();
            }
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }

    private void closeApp() {
        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
            Platform.exit();
        });
    }
}
