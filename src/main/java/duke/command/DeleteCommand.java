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
     * Prints String returned by getExecuteString method.
     * @param tasks TaskList that is being executed on.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printString(getExecuteString(tasks, ui, storage));
    }

    /**
     * Deletes task from TaskList, and updates user through Ui, after which
     * the task is removed from the text file through Storage. Returns String
     * of message from Ui.
     * @param tasks TaskList that the task is being deleted from.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     * @return String of message.
     * @throws DukeException if task number does not exist.
     */
    @Override
    public String getExecuteString(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.deleteTaskFromList(taskNumber);
            storage.writeToFile(tasks.getTasks());
            return ui.getDeletedTaskString(task, tasks.size());
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
