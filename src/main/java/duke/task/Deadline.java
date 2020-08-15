package duke.task;

/**
 * Represents an deadline duke.task
 */
public class Deadline extends Task {
    private final String schedule;

    public Deadline(String name, boolean isDone, String schedule) {
        super(name, isDone);
        this.schedule = schedule;
    }

    public String getSchedule() {
        return this.schedule;
    }

    @Override
    public Deadline complete() {
        return new Deadline(this.getName(), true, this.schedule);
    }

    @Override
    public String toString() {
        String taskString = super.toString();
        return "[D]" + taskString + " (by: " + this.getSchedule() + ")";
    }
}
