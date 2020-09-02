package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a DeleteCommand where user wants to delete a task.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Creates a DeleteCommand.
     *
     * @param taskNumber Index of task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to delete a task from list of tasks.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     * @return String displaying completion of Command.
     * @throws DukeException If command is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.remove(taskNumber);
            storage.save(tasks);
            return ui.showAction(String.format("  Noted. I've removed this task:\n"
                    + "    %s\n"
                    + "  Now you have %d tasks in the list.\n", t, tasks.size()));
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Can't delete a task that does not exist.");
        }
    }
}
