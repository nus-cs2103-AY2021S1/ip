package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    public LocalDateTime timing;

    public EventTask(String description, String timing) throws DukeException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            this.timing = LocalDateTime.parse(timing, formatter);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Error! Invalid date format, Please enter the date in the format dd-MM-yyyy HH:mm");
        }
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (by: " +
                timing.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")";
    }

}