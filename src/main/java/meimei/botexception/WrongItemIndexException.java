package meimei.botexception;

/**
 * Exception thrown when description of <code>DELETE</code> or <code>DONE</code>
 * user commands are not accompanied with a valid task number that is an integer
 * and within the range 1 to the size of the list of tasks (no tasks can be marked
 * or deleted if there are no task in the list).
 */
public class WrongItemIndexException extends BotException {

    /**
     * Public constructor.
     *
     * @param commandName Name of the command that the bot was trying to execute
     *                    when this exception was thrown.
     * @param listLength Length of the list of tasks saved in the bot.
     */
    public WrongItemIndexException(String commandName, int listLength) {
        super("Cannot find leh. Try typing \"" + commandName + " {index of list item}\"."
                + "\nYour list only got " + listLength + " things.");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
