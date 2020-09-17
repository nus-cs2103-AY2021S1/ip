package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.UI;
import bob.exception.BobException;
import bob.exception.BobNotAnEventException;
import bob.task.Event;
import bob.task.Task;

/**
 * This command when executed reschedules an event.
 */
public class RescheduleCommand extends Command {

    /** The index of the task on TaskList to be rescheduled. */
    private int index;

    /** The period that the task should be rescheduled to. */
    private String newPeriod;

    /**
     * Constructs a RescheduleCommand by assigning an integer to the index parameter, and a string to the newPeriod
     * parameter.
     *
     * @param index index of the task on the TaskList to be rescheduled.
     * @param newPeriod The period that the task should be rescheduled to.
     */
    public RescheduleCommand(int index, String newPeriod) {
        this.index = index;
        this.newPeriod = newPeriod;
    }

    /**
     * Executes the command to reschedule the task on the TaskList and updates Storage data accordingly.
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
        boolean isEvent = task instanceof Event;

        if (isEvent) {
            ((Event) task).reschedule(newPeriod);
            storage.updateSave(tasks);
            return ui.rescheduleTask(tasks, index);
        } else {
            throw new BobNotAnEventException();
        }
    }
}
