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

    public Event(String s, Boolean b, LocalDate t) {
        super(s, b);
        time = t;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
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


    /**
     * Returns true if both events have the same name and boolean and localdate.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Event otherConsultation = (Event) other;
        return otherConsultation.getName().equals(getName())
                && otherConsultation.isDone().equals(isDone())
                && otherConsultation.getTime().equals(getTime());
    }

}
