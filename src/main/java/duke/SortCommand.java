package duke;

import java.util.ArrayList;

/**
 * Controls logic of sorting tasks.
 */
class SortCommand extends Command {

    /** Contains user's sort order option, e.g.  asc, desc. */
    private SortOrderOption sortOrderOptionEnum;

    SortCommand(SortOrderOption sortOrderOptionEnum) {
        this.sortOrderOptionEnum = sortOrderOptionEnum;
    }

    /**
     * Executes list tasks.
     *
     * @param tasks Stores task list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> sortedTaskListString = tasks.sortTasks(sortOrderOptionEnum);
        return ui.showListTaskMessage(sortedTaskListString);
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
