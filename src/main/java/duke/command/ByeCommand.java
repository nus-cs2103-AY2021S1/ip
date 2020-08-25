package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

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
