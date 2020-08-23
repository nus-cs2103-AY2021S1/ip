package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;
    private LocalTime time;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
        this.time = null;
    }

    public Deadline(String description, Boolean isDone, LocalDate deadline) {
        super(description, isDone);
        this.deadline = deadline;
        this.time = null;
    }

    public Deadline(String description, LocalDate deadline, LocalTime time) {
        super(description);
        this.deadline = deadline;
        this.time = time;
    }

    public String getType() {
        return "D";
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return this.time != null
            ? "[" + this.getType()  + "]" + this.getStatusIcon() + " " + this.description + " (by:"
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + this.time.format(DateTimeFormatter.ISO_LOCAL_TIME) + ")"

                : "[" + this.getType()  + "]" + this.getStatusIcon() + " " + this.description + " (by:"
                + this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
    @Override
    public Deadline markAsDone() {
        //int index = taskNum - 1;
        Deadline newTask = new Deadline(this.getDescription(), true, this.deadline);
        return newTask;
    }
}
