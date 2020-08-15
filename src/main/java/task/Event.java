package task;

/**
 * Represents an event task
 */
public class Event extends Task {
    private final String schedule;
    public Event(String name, boolean isDone, String schedule) {
        super(name, isDone);
        this.schedule = schedule;
    }

    @Override
    public Event complete() {
        return new Event(this.getName(), true, this.schedule);
    }

    @Override
    public String toString() {
        String taskString = super.toString();
        String result = "[E]" + taskString + " (at: " + this.getSchedule() + ")";
        return result;
    }

    public String getSchedule() {
        return this.schedule;
    }
}