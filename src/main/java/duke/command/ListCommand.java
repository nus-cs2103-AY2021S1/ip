package duke.command;

import duke.TaskList;

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
    public String execute(String text, TaskList taskList) {
        return taskList.printStore();
    }
}
