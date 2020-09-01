package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

/**
 * Encapsulates a command to exit duke
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit duke
     *
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     * @return Output strings
     */
    @Override
    public String[] execute(Storage storage, TaskList tasks, Ui ui) {
        storage.resetFile();
        storage.saveTaskList(tasks);
        return ui.getExitStrings();
    }
}
