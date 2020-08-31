package sparrow.commands;

import sparrow.data.task.Todo;

public class AddTodoCommand extends AddTaskCommand {

    public static final String COMMAND_WORD = "todo";

    public AddTodoCommand(Todo toAdd) {
        super(toAdd);
    }
}
