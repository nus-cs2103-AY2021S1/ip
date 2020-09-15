package duke.task;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime byDateTime;

    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description, isDone);
        this.by = by;
        try {
            String reformatedDateTime = reformateDateTime();
            this.byDateTime = LocalDateTime.parse(reformatedDateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid input! Enter appropriate date and time format");
        }
    }

    public String toString() {
        String icon = this.completed ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]";
        String DateTime = this.byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyy HH:mm"));
        return "[D]" + icon + " " + this.description + " (by: " + DateTime + ")";
    }

    /**
     * Encode duke.task into a String to be saved in text file.
     *
     * @return String of encoded duke.task details.
     */
    public String toEncoding() {
        int completedBinary = this.completed ? 1 : 0;
        return "D>" + completedBinary + ">" + this.description + ">" + this.by;
    }

    /**
     * Converts the deadline details into a format readable by Java LocalDateTime API.
     * Format of "yyyy-mm-ddThh:mm:ss" required
     *
     * @return String of converted deadline details.
     */
    private String reformateDateTime() {
        String[] bySplit = this.by.split(" ", 2);
        String date = bySplit[0];
        String[] dateSplit = date.split("/", 3);
        String time = bySplit[1];
        String hour = time.substring(0, 2);
        String minute = time.substring(2);
        return dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0] + "T" + hour + ":" + minute + ":00";
    }

    public boolean isDate(LocalDate dateFilter) {
        return this.byDateTime.toLocalDate().equals(dateFilter);
    }
}
