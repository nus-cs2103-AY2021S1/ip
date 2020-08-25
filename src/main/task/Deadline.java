package main.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents tasks with a deadline.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class Deadline extends Task {
    private static final DateTimeFormatter FORMATTER
            = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");
    private final LocalDateTime time;

    /**
     * Constructs a Deadline instance with the name of
     * task and time of deadline.
     * @param name the name of task.
     * @param time the deadline of task.
     */
    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.time = time;
    }

    /**
     * Constructs a Deadline instance with the name of
     * task, time of deadline and the done state of the task.
     * @param name the name of task.
     * @param time the deadline of task.
     * @param doneState the done state of the task.
     */
    public Deadline(String name, String time, boolean doneState) {
        super(name, doneState);
        this.time = LocalDateTime.parse(time);
    }

    /**
     * Returns the string meant for writing to disk.
     * @return the string meant for writing to disk.
     */
    @Override
    public String write() {
        return String.format("D,%s%s", time, super.write());
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                time.format(FORMATTER));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline o = (Deadline) obj;
            return super.equals(o) && this.time.equals(o.time);
        }
        return false;
    }
}
