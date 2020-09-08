package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {

    private final String ICON = "[D]";
    private final DateTimeFormatter FORMATOFDATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private LocalDate date;

    /**
     * Class constructor.
     *
     * @param name Name of deadline.
     * @param date Date deadline is due.
     */
    public Deadline(String name, String date) {
        super(name);
        this.date = LocalDate.parse(date);
    }

    @Override
    public String toString() {
        return ICON + super.getStatusIcon() + " " + super.getTaskName() + " (by: " +
                date.format(FORMATOFDATE) + ")";
    }
}
