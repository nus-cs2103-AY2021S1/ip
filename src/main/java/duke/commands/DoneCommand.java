package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.tasks.Task;

/**
 * Represents the Command to mark a specific Task as done.
 */
public class DoneCommand implements Command {

    private final int taskNum;

    /**
     * Initializes a DoneCommand.
     *
     * @param taskNum The number of the task in the taskList to be marked as done.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks the Task at the specified taskNum as Done.
     *
     * @param storage The storage object.
     * @param tasks   The taskList.
     * @return The response to the user indicating the task has been marked as done.
     * @throws DukeException If taskNum index out of bounds.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) throws DukeException {
        try {
            Task doneTask = tasks.markTaskAsDone(taskNum);
            return "OK! I have marked the following task as done:\n" + doneTask.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
