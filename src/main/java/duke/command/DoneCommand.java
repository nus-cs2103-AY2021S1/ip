package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    /**
     * Index of {@link Task} to be marked as completed.
     */
    private final int index;

    /**
     * Instantiates a new DoneCommand object.
     * @param index The index of the {@link Task} to be marked as completed.
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Overrides execute in {@link Command}.
     * Executes the command to mark the {@link Task} as completed and save the changes to storage.
     * @param tasks The list of {@link Task}s.
     * @param ui The Ui object that is used by Duke.
     * @param storage The Storage object of Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (index > tasks.getSize()) {
                throw new InvalidArgumentException("☹ OOPS!!! The task index you give is not found.");
            }
            tasks.markDone(index);
            String msg = "Nice! I've marked this task as done:\n";
            msg += ("\t " + tasks.getTask(index));
            ui.displayMessage(msg);
        } catch (DukeException e) {
            ui.displayMessage(e.getMessage());
        }
    }
}
