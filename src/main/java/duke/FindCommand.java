package duke;

import java.util.ArrayList;

class FindCommand extends Command {

    /** Contains user's search string. */
    private String searchString;

    /**
     * Constructs find command.
     *
     * @param searchString String to find.
     */
    FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Executes finding of task.
     *
     * @param tasks Stores task list.
     * @param ui Handles user interaction.
     * @param storage Handles input output to hard disk.
     */
    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<String> foundTaskListString = tasks.findTasks(searchString);
        return ui.showFindTaskMessage(foundTaskListString);
    }

    /**
     * Checks if should exit program.
     *
     * @return Should exit program.
     */
    @Override
    boolean isExit() {
        return false;
    }
}
