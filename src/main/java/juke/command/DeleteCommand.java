package juke.command;

import juke.Storage;
import juke.TaskList;

/**
 * Represents the command to delete a task from the current list of tasks.
 */
public class DeleteCommand extends Command {

    private Integer index;

    /**
     * Constructs a DeleteCommand for a particular task.
     * @param index Index position of task to be deleted.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the removal of the task from the taskList.
     * @param taskList List of tasks
     * @param storage Storage of tasks onto disk
     * @return Response Text to be output by chatbot upon removal.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return taskList.removeFromList(this.index);
    }
}
