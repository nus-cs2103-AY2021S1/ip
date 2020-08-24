package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/** Represents the command that deletes a task when executed. */
public class DeleteCommand extends Command {

    private int taskIndex;

    /** Constructor.
     *
     * @param taskDeleted The index of the task to be deleted.
     */
    public DeleteCommand(int taskDeleted) {
        this.taskIndex = taskDeleted;
    }

    /** Deletes the task as specified by the constructor and displays the task
     * with a relevant message to the user.
     *
     * @param taskList The taskList involved.
     * @param ui The ui involved to show messages to the user.
     * @throws NoSuchTaskException If the taskDeleted is out of bounds.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws NoSuchTaskException {
        Task taskDeleted = taskList.deleteTask(taskIndex);
        ui.show(String.format("\t Noted. I've removed this task:\n\t\t%s\n\t %s",
                taskDeleted.toString(),
                taskList.tasksRemaining()
        ));
    }
}
