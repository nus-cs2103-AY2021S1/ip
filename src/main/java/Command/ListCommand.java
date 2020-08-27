package main.java.Command;

import main.java.TaskList;

/**
 * Represents command to list all current tasks.
 */
public class ListCommand extends Command {

    public ListCommand() {
        this.commandText = "list";
    }

    /**
     * Lists all current tasks.
     * @param text  unused argument.
     * @param taskList current list of tasks.
     */
    @Override
    public void execute(String text, TaskList taskList) {
        taskList.printStore();
    }
}
