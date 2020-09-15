package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that is invalid.
 */
public class InvalidCommand extends UserCommand {

    /**
     * @param userInput User's input.
     */
    public InvalidCommand(String userInput) {
        super(userInput);
    }

    /**
     * @param taskList TaskList that contains all the existing tasks.
     * @param ui Ui that helps to print output.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        throw new InvalidCommandException();
    }
}
