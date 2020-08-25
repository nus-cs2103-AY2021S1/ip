package duck.task;

import duck.ui.Colour;

import java.time.LocalDate;

public class Event extends TaskWithDate {

    public Event(String desc, LocalDate date) {
        super(desc, date);
    }

    @Override
    public String getStatus() {
        return Colour.Cyan("[E]") + super.getStatus() + " (at: " + super.getDateAsString() + ")";
    }
}
