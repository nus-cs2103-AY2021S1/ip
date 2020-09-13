package duke.task;

import java.time.LocalDate;

/** Represents an event task */
public class Event extends Task {
    /**
     * Initializes an event task.
     *
     * @param name The name of the event task.
     * @param isDone The boolean indicating if the task is done.
     * @param schedule The time to do the event.
     */
    public Event(String name, boolean isDone, LocalDate schedule) {
        super(name, isDone);
        this.schedule = schedule;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event complete() {
        return new Event(this.name, true, this.schedule);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String formatTask() {
        int isDoneSignal = this.isDoneTask() ? 1 : 0;
        return "E | " + isDoneSignal + " | " + this.name + " | " + this.getSchedule();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String taskString = super.toString();
        return "[E]" + taskString + " (at: " + this.getSchedule() + ")";
    }
}
