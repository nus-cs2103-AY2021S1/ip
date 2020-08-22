/**
 * Handles addition of todo-based Tasks.
 */

public class AddTodoCommand extends Command {
    /** Command details */
    private final String[] instructions;

    /**
     * Constructor for AddTodoCommand.
     * @param instructions Contains description.
     */
    public AddTodoCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    /**
     * Executes the AddTodo Command, adding a new Task of type Todo with description.
     * @param tasks TaskList to be added to.
     * @param ui For user interaction.
     * @param storage To store the added task.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task todo = new Todo(instructions[1]);
        tasks.addTask(todo);
        storage.save(tasks);
    }
}

