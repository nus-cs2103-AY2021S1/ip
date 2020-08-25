package duck.task;

import duck.ui.Colour;

import java.time.LocalDate;

/**
 * Event class for representing the event type.
 */
public class Event extends TaskWithDate {

    public Event(String desc, LocalDate date) {
        super(desc, date);
    }

    @Override
    public String getStatus() {
        return Colour.Cyan("[E]") + super.getStatus() + " (at: " + super.getDateAsString() + ")";
    }
}
