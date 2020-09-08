package duke.command;

import java.util.Objects;

import duke.task.Task;

/**
 * Adds a to do task to the list.
 * Inherits from AddAbstractTaskCommand which inherits from generic command class.
 */
public class TodoCommand extends AddAbstractTaskCommand {

    public TodoCommand(Task newTask) {
        super(newTask);
    }
}
