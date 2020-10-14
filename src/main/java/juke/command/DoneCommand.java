package juke.command;

import juke.Storage;
import juke.TaskList;

/**
 * Represents the command to mark a task from the current list of tasks, as done.
 */
public class DoneCommand extends Command {

    protected Integer index;

    /**
     * Constructs a DoneCommand for a particular task.
     * @param index Index position of task to be marked as done.
     */
    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the marking of a task as done.
     * @param taskList List of tasks
     * @param storage Storage of tasks onto disk
     * @return Response text to be output by chatbot upon marking.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return taskList.markTaskAsDone(this.index);
    }
}

