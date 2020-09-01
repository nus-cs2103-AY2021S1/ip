package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.utils.Ui;

/**
 * Represents command that change a task status to done in the Duke's task list upon execution
 */
public class DoneCommand extends Command {
    private int index;
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Changes a task status from the tasks provided to done and show done message through ui.
     *
     * @param tasks  a TaskList object which contains a list of task
     * @param ui a UI object which can prints message to console
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        Task task = tasks.get(index);
        tasks.markAsDone(index);
        ui.showDoneMessage(task);
    }
}
