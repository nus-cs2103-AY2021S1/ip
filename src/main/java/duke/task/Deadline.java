package duke.task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    public Deadline(String description, LocalDateTime time) {
        super(description, time);
        super.type = "deadline";
    }

    public Deadline(String description, LocalDateTime time, boolean isDone) {
        this(description, time);
        super.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", (
                super.isDone ? "\u2713" : "\u2718"),
                super.description,
                super.time);
    }
}
