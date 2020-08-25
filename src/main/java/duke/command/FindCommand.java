package duke.command;

import duke.component.*;
import duke.task.Task;

import java.util.LinkedList;

/**
 * The command to find tasks that contains the search keyword.
 */
public class FindCommand implements Command {
    private final String keyword;

    /**
     * Initializes a FindCommand with the given search keyword.
     * @param keyword the search keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Return whether this command is the exit command
     * @return false at all times
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to search tasks that contains the keyword
     * @param taskList the task list given
     * @param ui the ui object that handles inputs and outputs
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        TaskList results = new TaskList();
        for (Task task : taskList.getList()) {
            if (task.getDescription().contains(keyword)) {
                results.add(task);
            }
        }
        if (results.size() == 0) {
            ui.giveResponse("I cannot find any task containing " + keyword + " !");
        } else {
            ui.displayList(results, "Here are the matching tasks in your list:");
        }
    }
}
