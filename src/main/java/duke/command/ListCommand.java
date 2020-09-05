package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates a command that will show all the current tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by printing out the current task list.
     *
     * @param taskList  The list of tasks known by the chat bot.
     * @param ui        The Ui that is used by the chat bot.
     * @param storage   The storage used by the chat bot.
     * @return          Chat bot message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() == 0) {
            return ui.showError("No tasks found");
        }
        return ui.replyList(taskList.toString());
    }
}
