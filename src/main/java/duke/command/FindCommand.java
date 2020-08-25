package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;

public class FindCommand extends Command {

    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<Task> searchResults = taskList.findTasks(keyword);
        ui.printSearchResults(searchResults);
    }

    public boolean isExit() {
        return false;
    }
}
