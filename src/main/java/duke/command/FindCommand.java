package duke.command;

import duke.exception.EmptyFindException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Executes the finding of matching word in the task descriptions.
 */
public class FindCommand extends Command {

    private final String input;

    /**
     * Initializes the FindCommand class to look for the matching word.
     *
     * @param input Input string.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    /**
     * Finds all matching tasks based on the given user input.
     *
     * @param tasks Task List object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @throws EmptyFindException If no task matches the user input.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyFindException {
        if (input.isEmpty()) {
            throw new EmptyFindException();
        } else {
            TaskList taskList = tasks.matchAll(input);
            if (taskList.isEmpty()) {
                ui.emptyFind(input);
            } else {
                ui.showTaskList(taskList, "matching");
            }
        }
    }
}
