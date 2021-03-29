package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.UI;
import bob.exception.BobException;
import bob.exception.BobNotADeadlineException;
import bob.task.Deadline;
import bob.task.Task;

/**
 * This command when executed reschedules an event.
 */
public class SnoozeCommand extends Command {

    /** The index of the task on TaskList to be snoozed. */
    private int index;

    /** The deadline that the task should be snoozed to. */
    private String newDeadline;

    /**
     * Constructs a SnoozeCommand by assigning an integer to the index parameter, and a string to the newPeriod
     * parameter.
     *
     * @param index index of the task on the TaskList to be snoozed.
     * @param newDeadline The period that the task should be snoozed to.
     */
    public SnoozeCommand(int index, String newDeadline) {
        this.index = index;
        this.newDeadline = newDeadline;
    }

    /**
     * Executes the command to snooze the task on the TaskList and updates Storage data accordingly.
     * It also calls on the provided UI to return the appropriate messages.
     *
     * @param tasks the TaskList consisting of all tasks tracked by Bob.
     * @param ui the UI which prints out all messages corresponding to the Command.
     * @param storage the Storage which manages all saved data to be updated.
     * @return the message provided by the UI.
     * @throws BobException if exceptions are thrown.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws BobException {
        Task task = tasks.get(index);
        boolean isDeadline = task instanceof Deadline;

        if (isDeadline) {
            ((Deadline) task).snooze(newDeadline);
            storage.updateSave(tasks);
            return ui.snoozeTask(tasks, index);
        } else {
            throw new BobNotADeadlineException();
        }
    }
}
