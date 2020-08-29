package main.java.seedu.duke.commands;

import main.java.seedu.duke.Storage;
import main.java.seedu.duke.TaskList;
import main.java.seedu.duke.Ui;

/**
 * Represents the command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructor of ExitCommand.
     */
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
