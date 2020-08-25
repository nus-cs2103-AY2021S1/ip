package main.java.seedu.duke.commands;

import main.java.seedu.duke.TaskList;
import main.java.seedu.duke.Ui;
import main.java.seedu.duke.Storage;

/**
 * Represents the command to exit the program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit");
    }

    /**
     * Executes the command to exit the program.
     * @param tasks The task list that is involved.
     * @param ui The UI of Duke.
     * @param storage The storage of Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks);
        ui.closeScanner();
        ui.showByeMessage();
        return;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
