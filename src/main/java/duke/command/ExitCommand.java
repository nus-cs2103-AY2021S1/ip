package duke.command;

import java.util.Queue;

import duke.action.Action;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a call to close Duke.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks,
                        Queue<Action> commandQueue) {
        ui.exitMessage();
    }
}
