package duke.command;

import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Finds the tasks in TaskList matching keyword.
 */
public class FindCommand extends Command {

    protected String keyword;

    /**
     * Constructs a new instance of a FindCommand
     * @param keyword Keyword of task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     * @param taskList TaskList associated with command.
     * @param ui Ui associated with command.
     * @param storage Storage associated with command.
     *
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> searchResults = taskList.findTasks(keyword);
        return ui.printSearchResults(searchResults);
    }

    public boolean isExit() {
        return false;
    }
}
