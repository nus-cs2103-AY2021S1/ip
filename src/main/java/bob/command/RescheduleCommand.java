package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.UI;
import bob.exception.BobException;

import bob.exception.BobNotAnEventException;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;

public class RescheduleCommand extends Command {

    private int index;
    private String newPeriod;

    public RescheduleCommand(int index, String newPeriod) {
        this.index = index;
        this.newPeriod = newPeriod;
    }

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
