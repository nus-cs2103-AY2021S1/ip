package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeFrequencyNotFoundException;
import duke.exception.DukeInputNotRecognizedException;
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
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeFrequencyNotFoundException, DukeInputNotRecognizedException {
        String frequency = commandDetails[0];
        String[] taskDetails = commandDetails[1].split(" ");
        RecurringTask recurringTask = handleRecurring(frequency, taskDetails);
        tasks.getTasks().add(recurringTask);
        return ui.showTask(recurringTask, tasks.getTasks().size());
    }

    private RecurringTask handleRecurring(String frequency, String[] taskDetails)
            throws DukeFrequencyNotFoundException, DukeInputNotRecognizedException {
        if (taskDetails.length < 2) {
            throw new DukeFrequencyNotFoundException("Please give a time for daily recurring tasks.");
        }
        String time = taskDetails[1];
        if (time.length() > 4) {
            throw new DukeInputNotRecognizedException("Please input a time in the 24 hour format. Eg. 0600");
        }
        return RecurringTask.createRecurringTask(taskDetails[0], frequency, time);
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
