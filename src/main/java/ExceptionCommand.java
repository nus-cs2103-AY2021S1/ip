/**
 * Represents a ExceptionCommand command which is a subclass of Command.
 */
public class ExceptionCommand extends Command {
    public final static String INVALID_COMMAND = "IC";
    public final static String INVALID_INDEX = "II";
    public final static String NO_DESCRIPTION = "ND";
    public final static String NO_KEYWORD = "NC";
    public final static String INVALID_EVENT = "IE";
    public final static String INVALID_DATE_FORMAT = "IDF";
    public final static String INVALID_DEADLINE = "ID";

    private String exceptionType;

    /**
     * Creates a ExceptionCommand object.
     * Outputs various exception messages based on the exception type.
     *
     * @param exceptionType is the String identifier for the type of exception.
     */
    public ExceptionCommand(String exceptionType) {
        super("");
        this.exceptionType = exceptionType;
    }

    /**
     * Returns the exception message.
     *
     * @return a String of exception message as Duke response.
     */
    public String getOutput() {
        if (this.exceptionType.equals(INVALID_COMMAND)) {
            this.appendDukeMessage("*Invalid command.*"
                    + "\n  Duke Commands: bye, list, find, done, delete,"
                    + "\n                               todo, event, deadline"
                    + "\nPlease add a space character after find, done, delete, todo, event, deadline");

        } else if (this.exceptionType.equals(INVALID_INDEX)) {
            this.appendDukeMessage("*Invalid task index, please try again.*");

        } else if (this.exceptionType.equals(NO_DESCRIPTION)) {
            this.appendDukeMessage("*Please fill in task description*");

        } else if (this.exceptionType.equals(NO_KEYWORD)) {
            this.appendDukeMessage("*Please fill in a keyword to find*");

        } else if (this.exceptionType.equals(INVALID_EVENT)) {
            this.appendDukeMessage("*Please fill in event completion time in the following format:*"
                    + "\n  eg. event CCA meeting /at YYYY-MM-DD");

        } else if (this.exceptionType.equals(INVALID_DATE_FORMAT)) {
            this.appendDukeMessage("*Please fill in the time in the YYYY-MM-DD format*");

        } else if (this.exceptionType.equals(INVALID_DEADLINE)) {
            this.appendDukeMessage("*Please fill in deadline completion time in the following format:*"
                    + "\n  eg. deadline return book to Jurong Regional Library /by YYYY-MM-DD");
        }

        return this.toString();
    }
}
