package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;

    /**
     * Initializes Event object.
     *
     * @param description Description of the event.
     * @param at The date of the event. In the format yyyy-mm-dd.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    private String parseDateTime(String at) {
        LocalDate d = LocalDate.parse(at);
        return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Event information.
     *
     * @return Information regarding the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + parseDateTime(at) + ")";
    }

    /**
     * Event information in the format to be written to duke.txt.
     *
     * @return Information regarding the event.
     */
    @Override
    public String toFileString() {
        return "E\n" + super.getDone() + "\n" + super.toFileString() + "\n" + at + "\n\n";
    }
}