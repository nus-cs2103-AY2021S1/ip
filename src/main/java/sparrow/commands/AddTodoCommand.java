package sparrow.commands;

import sparrow.data.task.Todo;

public class AddTodoCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "todo";

    public static final String REQUIREMENT_MESSAGE = "A todo must include a description.";

    public AddTodoCommand(Todo toAdd) {
        super(toAdd);
    }
}
