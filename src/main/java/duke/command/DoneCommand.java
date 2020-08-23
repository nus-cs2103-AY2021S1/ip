package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {

    private int i;

    public DoneCommand(int i) {
        this.i = i;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task t = tasks.tasks.get(i - 1);
            t.markAsDone();
            ui.showAction(String.format("\t Nice! I've marked this task as done:\n"
                    + "\t   %s\n", t));
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("Can't complete a task that does not exist.");
        }
    }
}
