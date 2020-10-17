package duke.commands;

import duke.support.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an empty command when there is no access.
 */
public class EmptyCommand extends Command {
    public EmptyCommand() {
    }

    @Override
    public String run(TaskList taskList, Storage storage) {
        return Ui.accessMissing();
    }

}
