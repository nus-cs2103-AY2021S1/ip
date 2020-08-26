package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    private final int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Executes the command, deleting a task from the provided TaskList.
     *
     * @param tasks TaskList instance
     * @param ui Ui instance
     * @param storage Storage instance
     * @throws DukeException if the task cannot be found.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskNo);
        ui.showPrompt("Noted. I've removed this task:\n  "
                + task + "\n" + "Now you have " + tasks.getTasks().size()
                + (tasks.getTasks().size() == 1 ? " task" : " tasks") + " in the list.");
    }
}
