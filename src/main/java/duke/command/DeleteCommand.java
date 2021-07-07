package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    /** The index of the task to be deleted. */
    private final int taskNum;

    /**
     * Creates a new delete command to delete a task with the specified index.
     *
     * @param taskNum is the index of the task to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param tasks is the task list that the command will execute with.
     * @param storage is the storage that the command will execute with.
     * @return a command response after executing the delete command.
     * @throws DukeException when there is a problem executing the command.
     */
    @Override
    public CommandResponse execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            Task deletedTask = tasks.deleteTask(taskNum);
            storage.save(tasks.getTasks());
            return new CommandResponse(Ui.printDeleteTask(deletedTask, tasks), this.isExit());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("SORRY! The task number is not valid.");
        }
    }
}
