package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;
import duke.storage.Storage;

/**
 * Represents a call to create a new Todo Task.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private final String description;

    /**
     * Creates a new TodoCommand.
     * @param description String of user input to be entered as the description of a new Todo Task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Todo(description);
        tasks.add(t);
        ui.addTaskMessage(t, tasks);
    }
}
