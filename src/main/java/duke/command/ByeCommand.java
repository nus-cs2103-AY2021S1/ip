package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

/**
 * Represents a command which ends the operation.
 */
public class ByeCommand extends Command {

    /**
     * Executes the operation for the program to terminate.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.showBye();
    }

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
