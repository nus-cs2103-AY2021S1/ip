package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that is an invalid command
 */
public class InvalidCommand extends UserCommand {

    /**
     * @param userInput user's input.
     */
    public InvalidCommand(String userInput) {
        super(userInput);
    }

    /**
     * @param taskList task list containing all the tasks.
     * @param ui       ui that prints output.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        throw new InvalidCommandException();
    }
}
