package duke.command;

import java.util.Queue;

import duke.action.Action;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a call to list all Tasks in TaskList.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks,
                        Queue<Action> commandQueue) {
        ui.listTasksMessage(tasks);
    }
}
