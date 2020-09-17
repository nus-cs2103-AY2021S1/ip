package duke.commands;

import duke.data.task.Todo;

/**
 * Adds a Todo to the task list.
 */
public class AddTodoCommand extends AddCommand {

    public static final String COMMAND_WORD = "todo";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " :\nAdds a todo to the task list.\n"
            + "  Parameters: DESC\n"
            + "  Example: " + COMMAND_WORD + " read book";

    private final Todo toAdd;

    public AddTodoCommand(String description) {
        this.toAdd = new Todo(description);
    }

    @Override
    public CommandResult execute() {
        taskList.add(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd, taskList.size()));
    }

}
