package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;

/**
 * Represents a ListCommand.
 */
public class ListCommand extends Command {

    /**
     * Creates a ListCommand object.
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * Executes a list command.
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return List of tasks.
     * @throws EmptyListException For when the list is empty.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        return ui.printList(taskList);
    }
}
