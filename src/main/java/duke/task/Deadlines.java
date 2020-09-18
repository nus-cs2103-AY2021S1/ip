package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The class Deadlines denotes a Deadlines task.
 *
 * @author Alvin Chee
 */
public class Deadlines extends TimedTask {
    private String by;
    private LocalDateTime localDateTime;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");

    /**
     * Constructs a Deadlines task
     *
     * @param taskInfo  Task description information.
     * @param by  String description of time.
     */
    public Deadlines(String taskInfo , String by) {
        super(taskInfo, TaskType.DEADLINE);
        this.by = by;
        try {
            this.localDateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));;
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeParseException("Please tell me the date and time in YYYY-MM-DD hhmm format"
                    + " with the correct values\n\teg. deadline hackathon /by 2014-12-25 1630");
        }

    }

    /**
     * Returns string description of deadline.
     *
     * @return String description of deadline.
     */
    @Override
    public String returnTime() {
        return this.by;
    }

    /**
     * Return a done deadline task.
     *
     * @return A done deadline task.
     */
    @Override
    public Deadlines doneTask() {
        super.done = true;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), dateTimeFormatter.format(this.localDateTime));
    }
}
