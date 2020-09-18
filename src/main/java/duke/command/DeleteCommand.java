package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeTaskNotFoundException;
import duke.task.RecurringTask;
import duke.task.Task;

/**
 * Represents a command which deletes a task.
 */
public class DeleteCommand extends Command {

    private String[] commandDetails;

    /**
     * Creates a new instance of a DeleteCommand.
     *
     * @param commandDetails String array with task details.
     */
    public DeleteCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes the operation for an task to be deleted.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @return String that contains the executed DeleteCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeTaskNotFoundException {
        int taskNumber = Integer.parseInt(commandDetails[1]) - 1;
        if (tasks.getTasks().isEmpty() || taskNumber > tasks.getTasks().size()) {
            throw new DukeTaskNotFoundException(" ERROR... TASK NOT FOUND. \n PLEASE TRY AGAIN ");
        }
        if (tasks.getTasks().get(taskNumber) instanceof RecurringTask) {
            RecurringTask recurringTask = (RecurringTask) tasks.getTasks().get(taskNumber);
            recurringTask.cancelRepeat();
        }
        Task removedTask = tasks.getTasks().remove(taskNumber);
        return ui.showDeletedTask(removedTask, tasks.getTasks().size());
    }

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
