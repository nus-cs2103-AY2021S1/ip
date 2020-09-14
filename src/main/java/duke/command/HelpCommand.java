package duke.command;

/**
 * Abstraction for a help command parsed into Duke.
 * It displays all commands available to the user as well as the required syntax.
 */
public class HelpCommand implements Command {
    public static final String COMMAND_LIST = "Welcome to Duke! The full list of "
            + "commands are listed below:\n"
            + "1. save - Saves all changes to the task list to the hard drive.\n"
            + "2. help - Displays all available commands.\n"
            + "3. done [task index] - Marks the task at the specified index as "
            + "complete.\n"
            + "4. list - Displays all tasks, regardless of completion status.\n"
            + "5. delete [task index] - Deletes the task at the specified index.\n"
            + "6. todo [task description] - Adds a new ToDo task to the task list.\n"
            + "7. event [task description] /at [event date] - Adds a new Event "
            + "task to the task list that matches the given details.\n"
            + "8. deadline [task description] /by [due date] - Adds a new Deadline "
            + "task to the task list that matches the given details.\n"
            + "9. find [keyword] - Displays all tasks that contain the given keyword "
            + "in their description.";

    /**
     * Displays all available commands for Duke.
     *
     * @return List of all supported commands and operations.
     */
    @Override
    public String execute() {
        return COMMAND_LIST;
    }
}
