package duke.component;

import duke.command.InvalidCommandException;
import duke.task.Task;

public interface Storage {
    /**
     * Gets the list of tasks held by the storage.
     * @return the list of tasks
     */
    TaskList getList();

    /**
     * Writes the task to the storage file.
     * @param task the task that is to be written in the task
     * @throws InvalidCommandException should never been thrown unless the file path is not working
     */
    void addToList(Task task) throws InvalidCommandException;

    /**
     * Re-writes the storage file because of deletion or marking-as-done executed.
     * @param list the new task list used for updating the storage file
     * @throws InvalidCommandException should never been thrown unless the file path is not working
     */
    void reWrite(TaskList list) throws InvalidCommandException;
}
