package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

/**
 * Represents a Command where nothing needs to be done.
 * Used when an invalid user input is detected and nothing is done.
 */
public class EmptyCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {

    }
}
