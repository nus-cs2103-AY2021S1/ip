package duke.component;

import duke.command.InvalidCommandException;
import duke.task.Task;

public interface Storage {
    /**
     * Gets the list of tasks held by the storage.
     * @return the list of tasks
     */
    TaskList getList();

    void addToList(Task task) throws InvalidCommandException;

    void reWrite(TaskList list) throws InvalidCommandException;
}
