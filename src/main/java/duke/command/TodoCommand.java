package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Todo;

/**
 * Encapsulates a todo command to be executed by Duke.
 */
public class TodoCommand extends Command {
    private String description;

    /**
     * Creates a todo task to be added to both the TaskList and Storage.
     * @param description Description of the task.
     */
    public TodoCommand(String description) {
        super();
        this.description = description;
    }

    @Override
    public void execute(Storage storage, TaskList taskList) throws DukeException {
        taskList.addTask(new Todo(description));
        storage.saveTasks(taskList.getTask());
    }
}
