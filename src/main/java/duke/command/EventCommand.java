package duke.command;

import duke.task.Task;

/**
 * Adds an event task to the list.
 * Inherits from AddAbstractTaskCommand which inherits from generic command class.
 */
public class EventCommand extends AddAbstractTaskCommand {
    public static final String COMMAND_WORD = "event";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Adds an event task with time.\n\n"
        + "Fields: " + "[description]" + " /at " + "[time] \n"
        + "Example: " + COMMAND_WORD + " Meeting /at Noon";

    public EventCommand(Task newTask) {
        super(newTask);
    }
}
