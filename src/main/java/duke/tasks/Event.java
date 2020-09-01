package duke.tasks;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task implements Serializable{
    private LocalDate time;

    public Event(String s, LocalDate t) {
        super(s);
        time = t;
    }
    
    @Override
    public String toString() {
        return "[E]" + "[" + (super.isDone() ? "O" : "X") + "] " + super.getName() + " (at: "
                + time.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")) + ")";
    }
}
