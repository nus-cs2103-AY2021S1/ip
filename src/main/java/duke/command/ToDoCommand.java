package duke.command;


import duke.task.Task;
import duke.task.ToDo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Responsible for executing a todo command.
 */
public class ToDoCommand extends Command {
    private String description;

    /**
     * Constructs a ToDoCommand.
     *
     * @param description The description of the todo.
     */
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
