package main.java.duke.command;

import main.java.duke.core.Ui;
import main.java.duke.core.TaskList;
import main.java.duke.core.Storage;

/**
 * The ListCommand class represents a command that lists the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Takes in the task list, the interface, and the storage components, and list
     * the tasks in the task list.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     */
    @Override
    public void excecute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }
}
