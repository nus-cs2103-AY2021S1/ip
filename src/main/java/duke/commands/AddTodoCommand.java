package src.main.java.duke.commands;

import src.main.java.duke.data.task.Todo;


/**
 * Represents a command that adds a todo.
 */
public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    // Message to add
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a todo to the tasklist. "
            + "\nParameters: DESCRIPTION \n"
            + "Example: " + COMMAND_WORD
            + " revise homework";

    public static final String MESSAGE_SUCCESS = "New todo task added: %1$s";

    private final Todo newTask;

    public AddTodoCommand(String description) {
        this.newTask = new Todo(description);
    }

    @Override
    public CommandResult execute() {
        try {
            duke.addTask(newTask);
            return new CommandResult(String.format(MESSAGE_SUCCESS, newTask));
        } catch (Exception e) {
            return new CommandResult("Oh no. Add todo was not successful");
        }
    }
}
