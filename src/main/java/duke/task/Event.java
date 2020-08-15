package duke.task;

/**
 * Represents an event duke.task
 */
public class Event extends Task {
    private final String schedule;

    public Event(String name, boolean isDone, String schedule) {
        super(name, isDone);
        this.schedule = schedule;
    }

    public String getSchedule() {
        return this.schedule;
    }

    @Override
    public Event complete() {
        return new Event(this.getName(), true, this.schedule);
    }

    @Override
    public String toString() {
        String taskString = super.toString();
        return "[E]" + taskString + " (at: " + this.getSchedule() + ")";
    }
}
