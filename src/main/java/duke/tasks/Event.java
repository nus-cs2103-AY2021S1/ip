package duke.tasks;

import duke.Tag;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Event extends Task implements Serializable{
    private LocalDate time;

    public Event(String s, LocalDate t) {
        super(s);
        time = t;
    }
    
    @Override
    public String toString() {
        List<Tag> tags = super.getTags();
        String s = "";

        if (!tags.isEmpty()) {
            for (int i = 0; i < tags.size(); i++) {
                s += " #" + tags.get(i);
            }
        }
        return "[E]" + "[" + (super.isDone() ? "O" : "X") + "] " + super.getName() + " (at: "
                + time.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")) + ")" + s;
    }
}
