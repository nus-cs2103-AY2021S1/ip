/**
 * Encapsulates the different types of commands in an Enum.
 * Includes an invalid command.
 * Includes description as values.
 */
public enum Command {
    BYE ("Exits the app"),
    LIST ("Lists all the task you have"),
    DONE ("Marks a task as done, e.g. done 2"),
    TODO ("Adds a todo task, e.g. todo buy groceries"),
    DEADLINE ("Adds a deadline task, e.g. deadline physics homework /by 2020-09-06"),
    EVENT ("Adds an event task, e.g. event meeting /at 2020-08-01"),
    DELETE ("Deletes a task, e.g. delete 3"),
    FIND ("Searches for tasks that match the query"),
    HELP ("Brings up this help screen"),
    INVALID ("An invalid command");

    private String description;

    private Command(String description) {
        this.description = description;
    }

    /**
     * Retrieves the description of the command.
     *
     * @return command description.
     */
    public String getDescription() {
        return description;
    }

}
