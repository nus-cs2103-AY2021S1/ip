package duke.command;

import duke.common.DukeException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Deletes a task.
 */
public class DeleteCommand extends Command {
    private static int taskIndex;

    /**
     * Constructor for a new DeleteCommand object.
     *
     * @param taskIndex index of task in task list to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        DeleteCommand.taskIndex = taskIndex - 1;
    }

    /**
     * Executes the command.
     *
     * @param tasks list of tasks.
     * @param ui object to output messages.
     * @param storage object to write TaskList to file.
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Ui.display("Noted. I've removed this task:\n   "
                    + tasks.getTask(taskIndex));
            Ui.displayRemainingTasks(tasks);
            tasks.deleteTask(taskIndex);
            Storage.writeToFile(tasks.getList());
        } catch (Exception e) {
            throw new DukeException("This task does not exist!");
        }
    }
}
