package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Represents a command to delete tasks.
 */
public class DeleteCommand extends Command {

    /**
     * The task number on the list to be deleted.
     */
    private final String taskNumber;

    /**
     * Constructs a command that deletes a task.
     *
     * @param taskNumber The task number to be deleted.
     */
    public DeleteCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            Task task = manager.deleteTask(index);
            ui.showDeleteMessage(task, manager.getTasks().size());
            storage.saveTasks(manager.getTasks());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            // Invalid task number or number out of range
            String errorMessage = "Invalid task number! "
                    + "Please enter a valid task number :)\n";
            throw new DukeException(errorMessage);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}