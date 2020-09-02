package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command that will delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskNum;

    /**
     * Creates a new delete command with the specified number of the task to be deleted.
     *
     * @param taskNum the number of the task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes the task from the task list.
     *
     * @param tasks the task list where the task will be deleted from.
     * @param ui the ui that displays a message indicating that the task has been successfully deleted.
     * @param storage the storage where the tasks are saved after deleting.
     * @throws DukeException if the user enters an invalid task number.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.deleteTask(taskNum);
            ui.showDeleted(task, tasks);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Uh-oh! Looks like you have entered an invalid task number.");
        }
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}