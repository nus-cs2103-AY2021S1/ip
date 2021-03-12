/**
 * Represents a Task object which is a parent class of:
 * ByeCommand, DeadlineCommand, DeleteCommand, DoneCommand, EventCommand, ExceptionCommand, FindCommand,
 * ListCommand, TodoCommand.
 */
public class Command {
    private String dukeMessage;

    /**
     * Creates a Command object.
     * It is mainly for generating a Duke response.
     *
     * @param message is the output of the Command.
     */
    protected Command(String message) {
        dukeMessage = message;
    }

    /**
     * Adds the extraMessage to output of a Command.
     *
     * @param extraMessage is the String to add to original output.
     */
    protected void appendDukeMessage(String extraMessage) {
        dukeMessage = dukeMessage + extraMessage;
    }

    /**
     * Returns the String representation of Command output.
     *
     * @return Command output overall description.
     */
    @Override
    public String toString() {
        return dukeMessage;
    }
}
