package duke.command;

import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberException;
import duke.task.Task;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * This class represents the done command. When executed, the class will mark the specified task as done.
 */
public class DoneCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a Command to mark the specified task number as done.
     * @param taskNumber the task's number you want to mark as done
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the DoneCommand. Executing this command will mark the task corresponding to the task number as done
     * in the list as well as in the hard disk.
     * @param tasks TaskList of the current task.
     * @param ui Ui to deals with interactions with the user.
     * @param storage Storage to save the data to the hard disk.
     * @throws DukeException If the task number is less than 1
     * or exceed the TaskList size or storage fails to read the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            Task task = tasks.getTask(taskNumber - 1);
            task.setStatusToDone();
            storage.changeTaskStatusInFile(taskNumber);
            ui.sendMessage(ui.doneSuccess(task));
            return ui.doneSuccess(task);
        }
    }
}
