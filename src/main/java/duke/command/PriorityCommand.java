package duke.command;

import duke.ImageType;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;

/**
 * Represents a PriorityCommand.
 */
public class PriorityCommand extends Command {

    /**
     * Creates a PriorityCommand object.
     */
    public PriorityCommand() {
        super(CommandType.PRIORITY, ImageType.PENDING);
    }

    /**
     * Executes a priority command.
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Prompt for user to enter the task number.
     * @throws EmptyListException For when the list is empty.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        return ui.printPrompt("Which task do you want to assign a priority to?\n");
    }
}
