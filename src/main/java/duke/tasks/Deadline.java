package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDate time;

    public Deadline(String taskName, LocalDate time) {
        super(taskName);
        this.time = time;
    }

    public String getDataStorageName() {
        return "D | " + (super.getStatus() == "\u2713" ? "1" : "0") + " | " + super.getTaskName() + " | "
                + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
