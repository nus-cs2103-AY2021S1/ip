package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Todo;

/**
 * TodoCommand is an extension of a command.
 * It creates a todo task to be added to both the TaskList and Storage.
 */
public class TodoCommand extends Command {
    private String description;
    
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
