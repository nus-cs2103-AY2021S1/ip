package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberException;
import duke.task.Task;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * This class represents the delete command.
 * When executed, the class will delete the specified task.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a Command for delete with the specified task number
     * that want to be deleted.
     *
     * @param taskNumber the task's number you want to delete
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the DeleteCommand. Executing this command will
     * delete the task corresponding to the task number in the list as well as
     * in the hard disk. The Ui will shown the corresponding message based on if an
     * exception is thrown or not.
     *
     * @param tasks TaskList of the current task.
     * @param ui Ui to deals with interactions with the user.
     * @param storage Storage to save the data to the hard disk.
     * @throws DukeException If the task number is less than 1
     * or exceed the TaskList size or the storage fails to read the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            Task task = tasks.deleteTask(taskNumber - 1);
            storage.deleteTaskInFile(taskNumber);
            String message = ui.deleteSuccess(task, tasks.size());
            ui.sendMessage(message);
        }
    }
}
