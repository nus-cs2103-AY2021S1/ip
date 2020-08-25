package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

/**
 * Encapsulates a command to list the tasks in the current task list
 */
public class ListTasksCommand extends Command {

    /**
     * Executes the command to list the tasks in the current task list
     * @param storage Storage
     * @param tasks Task list
     * @param ui Ui
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        ui.printList(tasks);
    }
}
