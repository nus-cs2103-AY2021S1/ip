package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeFrequencyNotFoundException;
import duke.task.RecurringTask;

public class RecurringCommand extends Command {

    private String[] commandDetails;

    /**
     * Creates a new instance of a Recurring Command.
     *
     * @param commandDetails String array with task details.
     */
    public RecurringCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes the operation for an task that is recurring to be added.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @return String that contains the executed RecurringCommand.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeFrequencyNotFoundException {
        String date = commandDetails[0];
        String taskDetails = commandDetails[1];
        RecurringTask recurringTask = RecurringTask.createRecurringTask(taskDetails, date);
        tasks.getTasks().add(recurringTask);
        return ui.showTask(recurringTask, tasks.getTasks().size());
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
