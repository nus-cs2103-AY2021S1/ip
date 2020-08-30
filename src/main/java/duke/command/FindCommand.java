package duke.command;

import duke.exception.InvalidInputException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a FindCommand and handles methods related to commands
 * about finding matching tasks associated with a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs an FindCommand.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand by displaying the message to users
     * about the matching tasks found.
     *
     * @param storage The storage object.
     * @param taskList The taskList object.
     * @param ui The ui object.
     * @throws InvalidInputException If the input is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getListOfTasks()) {
            String name = task.getName();
            if (name.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.displayFinding(matchingTasks);
    }

    /**
     * Checks if the command is ExitCommand.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
