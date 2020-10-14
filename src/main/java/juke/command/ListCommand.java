package juke.command;

import juke.Storage;
import juke.TaskList;

/**
 * Represents the command to output list of tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the listing of tasks.
     * @param taskList List of tasks
     * @param storage Storage of tasks onto disk
     * @return List of stored tasks.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return taskList.printList();
    }

}
