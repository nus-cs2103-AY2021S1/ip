package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.Ui;

public class DoneCommand implements Command {
    private int index;
    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new InvalidArgumentException("index");
        }
        tasks.doneTask(index);
        ui.print("Nice! I've marked this task as done:\n" + tasks.getTask(index));

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
