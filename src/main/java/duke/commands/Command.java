package duke.commands;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Contains information parsed from an Ui object.
 * When the command is executed, the data structure will be modified and relevant message will be displayed.
 */
public abstract class Command {
    public Command() {
    }

    public boolean isExit() {
        return false;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

}
