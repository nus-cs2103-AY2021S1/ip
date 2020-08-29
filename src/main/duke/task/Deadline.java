package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate time;

    /**
     * Constructor for a Deadline object.
     * @param name the name for this Deadline task
     * @param time the time for the deadline
     */
    public Deadline(String name, LocalDate time) {
        super(name);
        this.time = time;
    }

    /**
     * Constructor for a Deadline object with the ability to set completion status.
     * @param name the name for this Deadline task
     * @param time the time for the deadline
     * @param completed the completion status of this Deadline.
     */
    public Deadline(String name, LocalDate time, String completed) {
        super(name, completed);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(),
                time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String[] toArray() {
        String[] strings = new String[4];
        strings[0] = "[D]";
        strings[1] = completed ? "1" : "0";
        strings[2] = name;
        strings[3] = time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return strings;
    }
}
