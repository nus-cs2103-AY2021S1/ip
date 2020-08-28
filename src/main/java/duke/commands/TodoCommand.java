package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Task;
import duke.tasks.TodoTask;

/**
 * Represents the Command to add a new TodoTask.
 */
public class TodoCommand implements Command {

    private final String description;

    /**
     * Initializes a TodoCommand.
     *
     * @param description The description of the TodoTask.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Adds a new TodoTask.
     *  @param storage The storage object.
     * @param tasks   The taskList.
     */
    @Override
    public String execute(Storage storage, TaskList tasks) {
        Task todoTask = new TodoTask(description);
        tasks.add(todoTask);
        return "Sure! I have added the following todo task to your list:\n" + todoTask.toString() + '\n' + tasks.getListStatus();
    }
}
