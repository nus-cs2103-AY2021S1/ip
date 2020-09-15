package duke.command;

import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;
import duke.task.Task;

/**
 * Represents a command which is used to set a task as done in the list
 * It is execute when the execute method is called
 *
 * @author Roger Lim
 */
public class DoneCommand extends Command {
    private int index;
    public DoneCommand(String index) {
        this.index = Integer.parseInt(index);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task done = tasks.markDone(index, storage);
        return ui.printDone(done);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
