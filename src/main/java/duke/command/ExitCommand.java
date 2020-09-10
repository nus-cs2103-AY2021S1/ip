package duke.command;

import duke.action.Action;
import duke.task.TaskList;

import duke.ui.Ui;

import duke.storage.Storage;

import duke.exception.DukeException;

import java.util.Queue;

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
    public void execute(Ui ui, Storage storage, TaskList tasks, Queue<Action> commandQueue) throws DukeException {
        ui.exitMessage();
    }
}
