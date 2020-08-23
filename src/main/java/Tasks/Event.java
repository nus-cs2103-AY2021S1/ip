package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDate at;

    public Event(String taskName, String at) {
        super(taskName);
        this.at = LocalDate.parse(at);
    }

    public LocalDate getAtDate() {
        return this.at;
    }

    public String getAt() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
