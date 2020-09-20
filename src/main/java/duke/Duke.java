package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Main class of bot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises a new instance of the bot.
     */
    public Duke(String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileName);
        this.tasks = new TaskList();

        try {
            storage.loadData(tasks);
            ui.fileLoaded();
        } catch (DukeException e) {
            ui.showErrorMsg(e);
            tasks = new TaskList();
        }
    }

    /**
     * Returns corresponding response according to user input.
     *
     * @param input User input.
     * @return Message response to user input.
     */
    public String getResponse(String input) {
        try {
            String uiMessage = "";
            Command c = Parser.parse(input);
            uiMessage = c.execute(ui, storage, tasks);
            return ui.getNextLine();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
