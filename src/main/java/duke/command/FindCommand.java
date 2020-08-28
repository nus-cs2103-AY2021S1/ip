package duke.command;

import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import duke.task.Task;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes a findTasks operation.
     * @param taskList TaskList to do the search operation on.
     * @param ui Ui responsible for the operation.
     * @param storage Storage associated with the operation.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> relatedTasks = taskList.findTasks(keyword);
        ui.printSearchResult(relatedTasks);
    }
}
