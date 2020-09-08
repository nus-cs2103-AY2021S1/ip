package duke.command;

import duke.action.Action;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import duke.ui.Ui;

import duke.storage.Storage;

import java.util.Queue;

/**
 * Represents a call to create a new Todo Task.
 */
public class TodoCommand extends Command {

    private final String description;

    public static final String COMMAND_WORD = "todo";

    /**
     * Creates a new TodoCommand.
     * @param description String of user input to be entered
     *                    as the description of a new Todo Task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks, Queue<Action> commandQueue) {
        Task t = new Todo(description);
        tasks.add(t);
        ui.addTaskMessage(t, tasks);
    }
}
