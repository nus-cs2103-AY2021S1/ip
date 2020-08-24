package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command when user exits Duke program.
 */
public class ByeCommand extends Command{
    @Override
    public void execute(TaskList task, Ui ui, Storage storage) {
       ui.print("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
