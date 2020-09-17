import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadlines are tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 */
public class Deadline extends Task {
    private static final int MAX_COMMAND_LENGTH = 2;
    private LocalDate date;


    public Deadline(String name, String dateString) {
        this(name, dateString, false);
    }

    Deadline(String name, String dateString, boolean isCompleted) {
        super(name, isCompleted);
        this.date = LocalDate.parse(dateString);
    }

    /**
     * Creates a deadline using a string command
     * @param description
     * @return a Deadline
     * @throws DukeException
     */
    public static Deadline create(String description) throws DukeException {
        String[] keywords = description.split(" /by ", 2);
        if (keywords.length < MAX_COMMAND_LENGTH) {
            throw new DukeException("☹ OOPS!!! Add a date using \" /by YYYY-MM-DD\".");
        }
        assert keywords.length == 2 : "description length should be 2";
        return new Deadline(keywords[0], keywords[1]);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + super.toSaveFormat() + " | " + date;
    }
}
