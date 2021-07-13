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
    /** The number of the task to be deleted */
    private final int taskNum;

    /**
     * Creates a new Delete Command with the specified number of the task to be deleted.
     *
     * @param taskNum the number of the task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes the task from the task list.
     *
     * @param tasks the task list.
     * @param ui the ui that will generate the deleted message.
     * @param storage the storage where the tasks will be saved.
     * @return the ui-generated message.
     * @throws DukeException if the user enters an invalid task number.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskNum);
        storage.save(tasks.getTasks());
        return ui.generateDeletedMessage(task, tasks);
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
