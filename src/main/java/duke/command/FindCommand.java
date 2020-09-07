package duke.command;

import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {

    protected String keyword;

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
