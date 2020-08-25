package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Command that marks task as done.
 */
public class DoneCommand extends Command {

    /** Task number of task to be marked as done. */
    private int taskNumber;

    /**
     * Creates a done command.
     * @param taskNumber task number of task.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks Task as done, updates the user through the Ui, and then
     * updates the text file through Storage.
     * @param tasks TaskList that is being executed on.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     * @throws DukeException if task number does not exist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.markTaskDoneInList(taskNumber);
            ui.printMarkedTask(task);
            storage.writeToFile(tasks.getTasks());
        } catch (DukeException e) {
            throw e;
        }
    }
}
