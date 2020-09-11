package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.UI;
import bob.exception.BobException;
import bob.exception.BobNotADeadlineException;
import bob.task.Deadline;
import bob.task.Task;

public class SnoozeCommand extends Command {

    private int index;
    private String newDeadline;

    public SnoozeCommand(int index, String newDeadline) {
        this.index = index;
        this.newDeadline = newDeadline;
    }

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
