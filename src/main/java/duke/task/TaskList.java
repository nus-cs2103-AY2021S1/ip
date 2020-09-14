package duke.task;

import duke.DukeException;
import duke.DukeList;
import duke.command.UserInputException;

/**
 * A task-list maintaining the state of the tasks.
 */
public class TaskList extends DukeList<Task> {

    /**
     * Constructs a new TaskList object with an empty task-list.
     */
    public TaskList() {}

    /**
     * Marks the task at the specified index of the task-list as done.
     *
     * @param index The index specifying the position in the task-list to mark the task as done (starts at 1).
     * @return The task that has been marked as done.
     * @throws DukeException If the index is out of bounds, either less than 1 or bigger than the size of
     * the task-list.
     */
    public Task markTaskAsDone(int index) throws DukeException {
        try {
            Task task = entries.get(index - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw UserInputException.INVALID_TASK_INDEX;
        }
    }

    /**
     * Deletes the entry at the specified index of the task-list.
     *
     * @param index The index specifying the position in the task-list to delete the entry (starts at 1).
     * @return The deleted entry.
     * @throws DukeException If the index is out of bounds (either less than 1 or bigger than the size of
     * the task-list.
     */
    public Task deleteTask(int index) throws DukeException {
        try {
            return entries.remove(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw UserInputException.INVALID_TASK_INDEX;
        }
    }

}
