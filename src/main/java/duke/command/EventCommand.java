package duke.command;

import duke.task.Task;

/**
 * Adds an event task to the list.
 * Inherits from AddAbstractTaskCommand which inherits from generic command class.
 */
public class EventCommand extends AddAbstractTaskCommand {

    public EventCommand(Task t) {
        super(t);
    }
}
