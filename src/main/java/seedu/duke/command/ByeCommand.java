package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;

/**
 * Represents a <code>Command</code> telling Duke to stop running.
 */
public class ByeCommand implements Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    public boolean isDone() {
        return true;
    }
}
