package duke.fxcommand;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.TodoTask;

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
     *
     * @param ui      The ui of Duke.
     * @param storage The storage object.
     * @param tasks   The taskList.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        Task todoTask = new TodoTask(description);
        tasks.add(todoTask);
        return "Sure! I have added the following todo task to your list:\n" + todoTask.toString() + '\n' + tasks.getListStatus();
    }
}
