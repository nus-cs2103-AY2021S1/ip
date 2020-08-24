package duke.commands;

import duke.exceptions.NoSuchTaskException;
import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;

/** Represents the command that marks a task as done when executed. */
public class DoneCommand extends Command {

    private int taskIndex;

    /** Constructor.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /** Marks the task, as specified by the taskIndex in the constructor, as done and
     * displays the task with a relevant message to the user.
     *
     * @param taskList The taskList involved.
     * @param ui The ui involved to show messages to the user.
     * @throws NoSuchTaskException If the taskIndex is out of bounds.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws NoSuchTaskException {
        Task taskDone = taskList.markTaskAsDone(taskIndex);
        ui.show(String.format("\t Nice! I've marked this task as done:\n\t\t%s", taskDone.toString()));
    }
}
