package duke.command;

import duke.ImageType;
import duke.TaskList;
import duke.Ui;
import duke.exception.EmptyListException;

/**
 * Represents a FindCommand.
 */
public class FindCommand extends Command {

    /**
     * Creates a FindCommand object.
     */
    public FindCommand() {
        super(CommandType.FIND, ImageType.PENDING);
    }

    /**
     * Executes a find command
     * @param ui Ui associated with the command.
     * @param taskList Task list associated with the command.
     * @return Prompt for user to enter a keyword.
     * @throws EmptyListException For when the list is empty.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) throws EmptyListException {
        if (taskList.isEmpty()) {
            throw new EmptyListException();
        }
        return ui.printPrompt("What are you trying to find? Search using a keyword.\n");
    }
}
