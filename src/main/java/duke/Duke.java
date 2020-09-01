package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidFileException;
import duke.tasks.TaskList;

/**
 * Duke class which initialize a Duke object that handles
 * all of the bot logic sequences.
 */

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
    }

    /**
     * Duke constructor to take initialize Storage, TaskList and Ui.
     * @param filePath path to saved file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (InvalidFileException e) {
            ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Main method for Duke to start running its processors.
     */
    public String run(String input) {
        String result = ui.showLine();
        try {
            Command c = Parser.parseCommand(input);
            return result + c.execute(tasks, ui, storage) + "\n" + ui.showLine();
        } catch (DukeException e) {
            return ui.showErrorMessage(e.getMessage());
        }
    }

    public String showWelcomeMessage() {
        return this.ui.showWelcomeMessage();
    }

    public String getResponse(String input) {
        return run(input);
    }
}
