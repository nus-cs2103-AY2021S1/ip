package duke;

/**
 * Handles addition of todo-based Tasks.
 */

public class AddTodoCommand extends Command {
    /** duke.Command details */
    private final String[] instructions;

    /**
     * Constructor for duke.AddTodoCommand.
     * @param instructions Contains description.
     */
    public AddTodoCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    /**
     * Executes the AddTodo duke.Command, adding a new duke.Task of type duke.Todo with description.
     * @param tasks duke.TaskList to be added to.
     * @param ui For user interaction.
     * @param storage To store the added task.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = new Todo(instructions[1]);
        return tasks.addTask(todo) + "\n" + storage.save(tasks);
    }
}
