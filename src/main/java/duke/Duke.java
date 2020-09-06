package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    /**
     * Task types used in Duke.
     */
    public enum TaskType {
        TODO("todo"),
        EVENT("event"),
        DEADLINE("deadline");

        public final String name;

        private TaskType(String name) {
            this.name = name;
        }
    }

    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Initializes a Duke object.
     *
     * @param pathName The path for the storage file.
     */
    public Duke(String pathName) {
        ui = new Ui();
        storage = new Storage(pathName);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    public CommandResult getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            CommandResult output = c.execute(tasks, storage);
            return output;
        } catch (DukeException e) {
            return new CommandResult(e.getMessage());
        }
    }

    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }
}
