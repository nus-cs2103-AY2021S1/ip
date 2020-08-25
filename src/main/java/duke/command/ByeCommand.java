package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class ByeCommand extends Command {
    /**
     * Overrides execute in {@link Command}.
     * Executes the command to print an exiting message.
     * @param tasks The list of {@link Task}s.
     * @param ui The Ui object that is used by Duke.
     * @param storage The Storage object of Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }

    /**
     * Overrides isExit in {@link Command}.
     * @return true to indicate an exiting instruction.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
