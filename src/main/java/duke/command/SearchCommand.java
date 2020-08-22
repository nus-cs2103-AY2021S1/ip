package main.java.duke.command;

import main.java.duke.core.Storage;
import main.java.duke.core.TaskList;
import main.java.duke.core.Ui;
import main.java.duke.handle.TaskNotFoundException;

import java.io.IOException;

/**
 * The SearchCommand class represents a command that searches for the task in the task list using a search key.
 */
public class SearchCommand extends Command {
    private String key;

    /**
     * Takes in the key for searching of the task and returns a search command.
     *
     * @param key
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
    public void excecute(TaskList taskList, Ui ui, Storage storage) throws TaskNotFoundException, IOException {
        ui.showList(taskList.findTaskWithDescription(key));
    }
}
