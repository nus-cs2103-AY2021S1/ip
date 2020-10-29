package duke.command;

import duke.task.Task;

/**
 * Adds a deadline task to the list.
 * Inherits from AddAbstractTaskCommand which inherits from generic command class.
 */
public class DeadlineCommand extends AddAbstractTaskCommand {
    public static final String COMMAND_WORD = "deadline";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Adds a deadline task with time.\n"
        + "Fields: " + "[description]" + " /by " + "[time] \n"
        + "Example: " + COMMAND_WORD + " Programming Assignment /by 2020-10-10 1800";

    public DeadlineCommand(Task newTask) {
        super(newTask);
    }
}
