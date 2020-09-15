package duke.task;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class Event extends Task {
    protected String at;
    protected LocalDateTime atDateTime;

    public Event(String description, String at, boolean isDone) throws DukeException {
        super(description, isDone);
        this.at = at;
        try {
            String reformatedDateTime = reformateDateTime();
            this.atDateTime = LocalDateTime.parse(reformatedDateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid input! Enter appropriate date and time format");
        }
    }

    public String toString() {
        String icon = this.completed ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]";
        String DateTime = this.atDateTime.format(DateTimeFormatter.ofPattern("MMM d yyy HH:mm"));
        return "[E]" + icon + " " + this.description + " (at: " + DateTime + ")";
    }

    /**
     * Encode task into a String to be saved in text file.
     *
     * @return String of encoded task details.
     */
    public String toEncoding() {
        int completedBinary = this.completed ? 1 : 0;
        return "E>" + completedBinary + ">" + this.description + ">" + this.at;
    }

    /**
     * Converts the event details into a format readable by Java LocalDateTime API.
     * Format of "yyyy-mm-ddThh:mm:ss" required
     *
     * @return String of converted event details.
     */
    private String reformateDateTime() {
        String[] bySplit = this.at.split(" ", 2);
        String date = bySplit[0];
        String[] dateSplit = date.split("/", 3);
        String time = bySplit[1];
        String hour = time.substring(0, 2);
        String minute = time.substring(2);
        return dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0] + "T" + hour + ":" + minute + ":00";
    }

    public boolean isDate(LocalDate dateFilter) {
        return this.atDateTime.toLocalDate().equals(dateFilter);
    }
}
