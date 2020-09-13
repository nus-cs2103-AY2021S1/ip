package duke;

import java.util.ArrayList;

class FindCommand extends Command {

    /** Contains user's search string. */
    private String search;

    /**
     * Constructs find command.
     *
     * @param search String to find.
     */
    FindCommand(String search) {
        this.search = search;
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
        ArrayList<String> foundTaskListString = tasks.findTasks(search);
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
