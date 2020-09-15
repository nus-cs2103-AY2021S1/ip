package ekud.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected LocalDate at;

    /**
     * Instantiates a new Event.
     *
     * @param description the description of the event as a <code>String</code>
     * @param at          the date at which the event takes place
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public Event copy() {
        Event ret = new Event(description, at);
        ret.isDone = isDone;

        return ret;
    }

    @Override
    public String toString() {
        String eventAt = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(at);
        return "[E]" + super.toString() + " (at: " + eventAt + ")" + "\n";
    }
}
