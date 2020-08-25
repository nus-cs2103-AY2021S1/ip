package duck.task;

import duck.Colour;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends TaskWithDate {

    public Deadline(String desc, LocalDate date) {
        super(desc, date);
    }

    @Override
    public String getStatus() {
        return Colour.Magenta("[D]") + super.getStatus() + " (by: " + super.getDateAsString() + ")";
    }
}
