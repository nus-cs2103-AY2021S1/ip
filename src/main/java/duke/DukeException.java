package duke;

/**
 * Represents an Exception encountered by Duke.
 */
public class DukeException extends Exception {
    String description;

    public static final DukeException INVALID_COMMAND_EXCEPTION = new DukeException("Wat talking you?");
    public static final DukeException INVALID_TASK_EXCEPTION = new DukeException("The description cannot be empty!");
    public static final DukeException INVALID_DEADLINE_EXCEPTION =
            new DukeException("Deadline not provided! \n Please provide deadline in the following format: \n" +
                    "deadline [description] /by [deadline in YYYY/MM/DD] \n");
    public static final DukeException INVALID_TIME_EXCEPTION =
            new DukeException("Time not provided! \n Please provide time in the following format: \n" +
                    "event [description] /at [time in YYYY/MM/DD HHMM] \n");
    public static final DukeException INVALID_INDEX_EXCEPTION = new DukeException("Excuse me... please check index... >:(");
    public static final DukeException INVALID_QUERY_EXCEPTION = new DukeException("Wat you looking for again?");
    public static final DukeException INVALID_SORT_EXCEPTION = new DukeException("I dun gets... \n Accepted requests are: \n" +
            "1. sort /type \n2. sort /date");

    /**
     * Returns a new DukeException with the given {@code String} description.
     */
    public DukeException(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Sorry (not sorry)!! " + description;
    }
}
