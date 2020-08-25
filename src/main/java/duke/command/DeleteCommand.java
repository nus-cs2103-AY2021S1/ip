package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command that deletes task from TaskList.
 */
public class DeleteCommand extends Command {

    /** Task number of Task to be deleted from TaskList. */
    private int taskNumber;

    /**
     * Creates a delete command.
     * @param taskNumber Task number of Task.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes task from TaskList, and updates user through Ui, after which
     * the task is removed from the text file through Storage.
     * @param tasks TaskList that the task is being deleted from.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     * @throws DukeException if task number does not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.deleteTaskFromList(taskNumber);
            ui.printDeletedTask(task, tasks.size());
            storage.writeToFile(tasks.getTasks());
        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Returns taskNumber of the DeleteCommand.
     * @return taskNumber.
     */
    public int getTaskNumber() {
        return taskNumber;
    }
}
