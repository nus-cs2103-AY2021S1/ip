package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Duke chat-bot, called Nite.
 * Nite is an interactive app which helps to keep track of tasks.
 * @author Chia Wen Ling
 * @version v0.1
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Duke.
     * Initialises the Ui, Storage and TaskList upon starting.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("/data/duke.txt", "/data");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Returns the response for the input command.
     *
     * @param input User input.
     * @return Response to user's input.
     */
    public String getResponse(String input) {
        boolean isExit = false;
        try {
            Command command = Parser.parse(input);
            isExit = command.isExit();
            if (isExit) {
                // System.exit(0);
                return ui.showFarewell();
            }
            return command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
