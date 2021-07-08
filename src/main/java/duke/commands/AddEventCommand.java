package src.main.java.duke.commands;

import src.main.java.duke.data.task.Event;


/**
 * Represents a command that adds an event.
 */
public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    // Message to add
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a event to the tasklist. "
            + "\nParameters: DESCRIPTION [/at] DUEDATE TIME \n"
            + "Example: " + COMMAND_WORD
            + " mark homework /at 2019-02-12 18:00";

    public static final String MESSAGE_SUCCESS = "New event task added: %1$s";

    private final Event newTask;

    public AddEventCommand(String description, String dueDate) {
        this.newTask = new Event(description, dueDate);
    }

    @Override
    public CommandResult execute() {
        try {
            duke.addTask(newTask);
            return new CommandResult(String.format(MESSAGE_SUCCESS, newTask));
        } catch (Exception e) {
            return new CommandResult("Oh no. Add event was not successful");
        }
    }
}
