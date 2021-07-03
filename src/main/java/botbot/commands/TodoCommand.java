package botbot.commands;

import botbot.tasks.Todo;

/**
 * Adds a to-do to the task list.
 */
public class TodoCommand extends AddCommand {
    public static final String COMMAND_KEYWORD = "todo";

    /**
     * Creates a to-do command to add a to-do.
     *
     * @param description Description of to-do.
     */
    public TodoCommand(String description) {
        super(new Todo(description));
    }
}
