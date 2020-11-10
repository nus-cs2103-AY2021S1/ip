package meimei.botexception;

/**
 * Exception thrown when the description is missing from is missing for the
 * <code>AddCommand</code>, <code>DeleteCommand</code> or <code>DoneCommand</code>
 * bot commands.
 */
public class NoDescriptionException extends BotException {

    /**
     * Public constructor.
     *
     * @param commandName Name of the type of command that the bot was trying to create
     *                    when the exception was thrown.
     */
    public NoDescriptionException(String commandName) {
        super("The description of " + commandName + " cannot be empty lah. Try again!");
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
