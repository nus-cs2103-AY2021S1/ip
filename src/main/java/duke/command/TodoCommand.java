package duke.command;

import duke.task.Task;

/**
 * Adds a to do task to the list.
 * Inherits from AddAbstractTaskCommand which inherits from generic command class.
 */
public class TodoCommand extends AddAbstractTaskCommand {
    public static final String COMMAND_WORD = "todo";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Adds a todo task.\n"
        + "Fields: " + "[description] \n"
        + "Example: " + COMMAND_WORD + " Finish assignment";

    public TodoCommand(Task newTask) {
        super(newTask);
    }
}
