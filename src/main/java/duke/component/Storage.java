package duke.component;

import duke.command.InvalidCommandException;
import duke.task.Task;

public interface Storage {
    public TaskList getList();

    public void addToList(Task task) throws InvalidCommandException;

    public void reWrite(TaskList list) throws InvalidCommandException;
}
