package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected boolean hasTime = true;
    protected LocalDate time;

    /**
     * Constructs a deadline object, used when user add new task.
     *
     * @param description Title of the deadline.
     * @param time The deadline date.
     */
    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Constructs a deadline object, used when read data from data.txt.
     *
     * @param description Title of the deadline.
     * @param isDone Whether the task is completed.
     * @param time The deadline date.
     */
    public Deadline(String description, int isDone, LocalDate time) {
        super(description, isDone);
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }

    public Boolean matchTime(LocalDate date) {
        return getTime().isEqual(date);
    }

    public boolean getHasTime() {
        return hasTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String timeString = time.format(formatter);
        return super.toString().replace("[", "[D][") + " (by: " + timeString + ")";
    }

    @Override
    public String getData() {
        return "D" + super.getData() + " | " + time;
    }

}
