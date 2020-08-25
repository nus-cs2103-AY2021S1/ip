package src.main.java.duke.commands;

import src.main.java.duke.data.task.Todo;

public class AddTodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";

    // Message to add
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a todo to the tasklist. "
            + "\nParameters: DESCRIPTION \n"
            + "Example: " + COMMAND_WORD
            + " revise homework";

    public static final String MESSAGE_SUCCESS = "New todo task added: %1$s";

    private final Todo toAdd;

    public AddTodoCommand(String description) {
        this.toAdd = new Todo(description);
    }

    @Override
    public CommandResult execute() {
        try {
            duke.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (Exception e) {
            return new CommandResult("send help");
        }
    }
}