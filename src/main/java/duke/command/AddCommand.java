package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * {@code AddCommand} is a child of {@code Command} object.
 *      On execution, it will add a given task into the task list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Constructs a AddCommand which adds a new task to the list on execution.
     *
     * @param t The task to be added into the list of tasks.
     */
    public AddCommand (Task t) {
        this.task = t;
    }

    /**
     * Adds task into task list.
     *
     * @param tasks The list of task.
     * @param ui The displaying user interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.add(this.task);
        ui.showAddTask(this.task, tasks.size());
    }
}
