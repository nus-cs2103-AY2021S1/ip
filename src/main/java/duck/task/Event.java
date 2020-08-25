package duck.task;

import duck.Colour;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends TaskWithDate {

    public Event(String desc, LocalDate date) {
        super(desc, date);
    }

    @Override
    public String getStatus() {
        return Colour.Cyan("[E]") + super.getStatus() + " (at: " + super.getDateAsString() + ")";
    }
}
