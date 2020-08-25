package duke.command;

import duke.util.Storage;
import duke.task.Task;
import duke.util.TaskList;
import duke.task.ToDo;
import duke.util.Ui;

/**
 * Responsible for executing a todo command.
 */
public class ToDoCommand extends Command {
    String description;

    public ToDoCommand(String description) {
        super(true);
        this.description = description;
    }

    /**
     * Executes a todo command.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(description);
        tasks.add(task);
        storage.save(tasks);
        String response = String.format(
                "I've added this task:\n  %s \nNow you have %s tasks in the list.",
                task, tasks.size()
        );
        ui.printResponse(response);
    }

    @Override
    public String toString() {
        return "todo <task>";
    }
}
