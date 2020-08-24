package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    private Deadline (String task, LocalDateTime deadline, boolean isDone) {
        super(task, isDone);
        this.deadline = deadline;
    }

    public Deadline(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public Deadline markDone() {
        return new Deadline(task, deadline, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof Deadline) {
            Deadline d = (Deadline) o;
            return d.task.equals(this.task) && d.deadline.equals(deadline);
        } else {
            return false;
        }
    }

    @Override
    public LocalDate getDate() {
        return deadline.toLocalDate();
    }

    @Override
    public String saveFormat() {
        return "D" + super.saveFormat() + String.format("%sT%s",
                deadline.format(DateTimeFormatter.ofPattern("y-MM-dd")),
                deadline.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)",
                deadline.format(DateTimeFormatter.ofPattern("dd MMM y, h:mm a")));
    }
}