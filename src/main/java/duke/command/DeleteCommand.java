package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.utils.Ui;

/**
 * Represents command that delete a task from the duke.KK's task list upon execution
 */

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes a task from the tasks provided and show removal message through ui.
     *
     * @param tasks  a TaskList object which contains a list of task
     * @param ui a UI object which can prints message to console
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return ui.showRemovalMessage(task, tasks);
    }
}
