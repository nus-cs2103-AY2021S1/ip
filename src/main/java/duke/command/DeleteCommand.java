package duke.command;

import duke.ImageType;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;

/**
 * Represents a DeleteCommand.
 */
public class DeleteCommand extends Command {

    /**
     * Creates a DeleteCommand object.
     */
    public DeleteCommand() {
        super(CommandType.DELETE, ImageType.PENDING);
    }

    /**
     * Executes a delete command.
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Prompt for user to enter the task number to be deleted.
     * @throws EmptyListException For when the list is empty.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        return ui.printPrompt("Which task do you want to delete?\n");
    }
}
