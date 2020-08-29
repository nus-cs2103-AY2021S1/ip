package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate time;

    /**
     * Constructor for a Event object.
     * @param name the name for this Event task
     * @param time the time for the event
     */
    public Event(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    /**
     * Constructor for a Event object with the ability to set completion status.
     * @param name the name for this Event task
     * @param time the time for the event
     * @param completed the completion status of this Event.
     */
    public Event(String name, LocalDate time, String completed) {
        super(name, completed);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(),
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }


    @Override
    public String[] toArray() {
        String[] strings = new String[4];
        strings[0] = "[E]";
        strings[1] = completed ? "1" : "0";
        strings[2] = name;
        strings[3] = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return strings;
    }
}
