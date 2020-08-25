package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class InvalidCommand extends UserCommand{
    public InvalidCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        throw new InvalidCommandException();
    }
}
