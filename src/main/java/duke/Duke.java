package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.application.Platform;

/**
 * Represents the main class for the Duke application.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs an instance of Duke.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns Duke's response to a command after executing it.
     *
     * @param input User command.
     * @return Duke's response to the command.
     */
    public String getResponse(String input) {
        String response;

        try {
            Command command = Parser.parse(input);
            response = command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response = e.getMessage();
        }

        if (response.equals("Bye. Hope to see you again soon!")) {
            Platform.exit();
        }

        return response;
    }
}
