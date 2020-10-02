package duke.command;

import duke.ImageType;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;

/**
 * Represents a DoneCommand.
 */
public class DoneCommand extends Command {

    /**
     * Creates a DoneCommand object.
     */
    public DoneCommand() {
        super(CommandType.DONE, ImageType.PENDING);
    }

    /**
     * Executes a done command.
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Prompt for the user to enter the task number.
     * @throws EmptyListException For when the list is empty.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        return ui.printPrompt("Which task do you want to mark as done?\n");
    }
}
