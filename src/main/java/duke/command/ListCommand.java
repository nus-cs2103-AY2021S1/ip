package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Encapsulates a command that will list all the tasks of the chatbot..
 */
public class ListCommand extends Command {
    /**
     * Executes the List command by printing out all tasks in the task list.
     *
     * @param tasks   The list of tasks known by the chatbot.
     * @param ui      The UI that is used by the chatbot.
     * @param storage The storage that is used by the chatbot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.print("No tasks found.");
        } else {
            ui.print(String.format("Here are the tasks in your list:\n%s", tasks));
        }
    }
}
