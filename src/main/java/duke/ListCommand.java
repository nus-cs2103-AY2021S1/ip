package duke;

import java.util.ArrayList;

/**
 * Controls logic of listing tasks.
 */
class ListCommand extends Command {

    /**
     * Executes list tasks.
     *
     * @param tasks Stores task list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> taskListString = tasks.listTasks();
        return ui.showListTaskMessage(taskListString);
    }

    /**
     * Checks if should exit program.
     *
     * @return Should not exit program.
     */
    @Override
    boolean isExit() {
        return false;
    }
}
