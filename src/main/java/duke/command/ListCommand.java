package main.java.duke.command;

import main.java.duke.core.*;

/**
 * The ListCommand class represents a commmand that lists the tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Takes in the task list, the interface, and the storage compoenents, and list
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
