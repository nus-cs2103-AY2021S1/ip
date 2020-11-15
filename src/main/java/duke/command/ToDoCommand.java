package duke.command;

import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;
import duke.util.Storage;
import duke.util.TaskList;

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
     * Executes a todo command and returns a response.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @return Message when the command is completed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(description);
        int previousTaskSize = tasks.size();
        tasks.add(task);
        int subsequentTaskSize = tasks.size();
        assert (previousTaskSize + 1 == subsequentTaskSize);
        storage.save(tasks);
        return String.format(
                "I've added this task:\n%s\nNow you have %s tasks in the list.",
                task, tasks.size()
        );
    }

    @Override
    public String toString() {
        return "todo <description>";
    }
}
