package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.task.TaskList;

public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

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
