package src.main.java.duke.commands;

import src.main.java.duke.data.task.Deadline;

/**
 * Represents a command that adds a deadline.
 */
public class AddDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    // Message to add
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a deadline task to the tasklist. "
            + "\nParameters: DESCRIPTION [/by] DUEDATE TIME \n"
            + "Example: " + COMMAND_WORD
            + " do homework /by 2019-02-12 18:00";

    public static final String MESSAGE_SUCCESS = "New deadline task added: %1$s";

    private final Deadline newTask;

    public AddDeadlineCommand(String description, String dueDate) {
        this.newTask = new Deadline(description, dueDate);
    }

    @Override
    public CommandResult execute() {
        try {
            duke.addTask(newTask);
            return new CommandResult(String.format(MESSAGE_SUCCESS, newTask));
        } catch (Exception e) {
            return new CommandResult("Oh no. Add deadline was not successful");
        }
    }
}
