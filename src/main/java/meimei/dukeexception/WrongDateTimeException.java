package meimei.dukeexception;

import meimei.command.CommandType;

/**
 * Exception thrown when date and time is not given in the correct format
 * for <code>DEADLINE</code> and <code>EVENT</code> user commands from
 * <code>CommandType</code>.
 */
public class WrongDateTimeException extends DukeException {

    /**
     * Public constructor.
     *  @param commandType Type of command i.e. deadline/event.
     * @param separator Between description and date & time.
     */
    public WrongDateTimeException(CommandType commandType, String separator) {
        super("You type wrong lah!" + "\nTry \"" + commandType.toString().toLowerCase()
                + " {description of task} " + separator
                + " {date and time in this format: dd/MM/yyyy HH:mm}\"");
    }
}
