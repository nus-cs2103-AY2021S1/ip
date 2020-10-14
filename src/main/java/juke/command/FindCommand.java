package juke.command;

import juke.Storage;
import juke.TaskList;

/**
 * Represents the command to search for a task based on given String.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a FindCommand with a given keyword.
     * @param keyword A String used to search for a task.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Executes the search for tasks based on keyword.
     * @param taskList List of tasks
     * @param storage Storage of tasks onto disk
     * @return List of tasks that match the input keyword.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return taskList.findTasks(this.keyword);
    }
}
