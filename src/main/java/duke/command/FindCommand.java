package duke.command;

import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command.
     * @param taskList TaskList associated with command.
     * @param ui Ui associated with command.
     * @param storage Storage associated with command.
     * @throws DukeException If there is error during execution of command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<Task> searchResults = taskList.findTasks(keyword);
        ui.printSearchResults(searchResults);
    }

    public String getKeyword() {
        return this.keyword;
    }

    public boolean isExit() {
        return false;
    }
}
