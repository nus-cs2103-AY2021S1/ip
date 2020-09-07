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
     * Takes the input argument, executes a command based on the input, and returns a command result object
     *
     * @param input The string input from user
     * @return CommandResult object from the execution of a command
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
