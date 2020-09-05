package duke.command;

import java.io.IOException;

import duke.core.Result;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.handle.TaskNotFoundException;

/**
 * The SearchCommand class represents a command that searches for the task in the task list using a search key.
 */
public class SearchCommand extends Command {
    private String key;

    /**
     * Takes in the key for searching of the task and returns a search command.
     *
     * @param key The search key.
     */
    public SearchCommand(String key) {
        this.key = key;
    }

    /**
     * Takes in the task list, the interface, and the storage components, and list
     * the tasks with description that contains the search key.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     * @throws TaskNotFoundException If there is no task corresponding to the count of the task.
     * @throws IOException If the storage process needs to be handled
     */
    @Override
    public Result excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {
        return new Result(ui.getTaskListMessage(taskList.findTaskWithDescription(key)), this.isContinuing());
    }
}
