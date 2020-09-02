package nite;

import nite.command.Command;
import nite.exception.NiteException;
import nite.parser.Parser;
import nite.storage.Storage;
import nite.task.TaskList;
import nite.ui.Ui;

/**
 * Represents the Duke chat-bot, called Nite.
 * Nite is an interactive app which helps to keep track of tasks.
 * @author Chia Wen Ling
 * @version v0.1
 */
public class Nite {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Duke.
     * Initialises the Ui, Storage and TaskList upon starting.
     */
    public Nite() {
        ui = new Ui();
        storage = new Storage("/data/duke.txt", "/data");
        try {
            tasks = new TaskList(storage.load());
        } catch (NiteException e) {
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
        } catch (NiteException e) {
            return ui.showError(e.getMessage());
        }
    }
}
