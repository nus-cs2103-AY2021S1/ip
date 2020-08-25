package duke.command;

import duke.task.Task;

/**
 * Adds a deadline task to the list.
 * Inherits from AddAbstractTaskCommand which inherits from generic command class.
 */
public class DeadlineCommand extends AddAbstractTaskCommand {

    public DeadlineCommand(Task t) {
        super(t);
    }
}
