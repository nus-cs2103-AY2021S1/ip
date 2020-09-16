package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The class Events denotes a Events task.
 *
 * @author Alvin Chee
 */
public class Events extends TimedTask {
    private String at;
    private LocalDateTime localDateTime;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");

    /**
     * Constructs a Events task
     *
     * @param taskInfo  Task description information.
     * @param at  String description of time.
     */
    public Events(String taskInfo , String at) {
        super(taskInfo, TaskType.EVENT);
        this.at = at;
        try {
            this.localDateTime = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));;
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParseException(
                "Please tell me the date and time in YYYY-MM-DD hhmm format"
                    + " with the correct values\n\teg. event hackathon /at 2014-12-25 1630");
        }

    }

    /**
     * Returns string description of deadline.
     *
     * @return String description of deadline.
     */
    @Override
    public String returnTime() {
        return this.at;
    }

    /**
     * Return a done events task.
     *
     * @return A done events task.
     */
    @Override
    public Events doneTask() {
        super.done = true;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)",
            super.toString(),
            dateTimeFormatter.format(this.localDateTime));
    }

}
