package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a FindCommand where user wants to find tasks matching a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Creates a FindCommand.
     *
     * @param keyword Input keyword to be found in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find and display matching tasks in tasklist.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String tasksString = tasks.findTasks(keyword);
        ui.showAction(String.format("\t Here are the tasks that match %s:\n" + tasksString, keyword));
    }
}