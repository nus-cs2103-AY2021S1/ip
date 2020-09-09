package duke.task;

import java.time.LocalDate;

/** Represents an deadline task */
public class Deadline extends Task {
    /**
     * Creates an instance of deadline object.
     *
     * @param name The name of the deadline task.
     * @param isDone The boolean indicating if the task is done.
     * @param schedule The deadline of the task.
     */
    public Deadline(String name, boolean isDone, LocalDate schedule) {
        super(name, isDone);
        this.schedule = schedule;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deadline complete() {
        return new Deadline(this.name, true, this.schedule);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String formatTask() {
        int isDoneSignal = this.isDoneTask() ? 1 : 0;
        return "D | " + isDoneSignal + " | " + this.name + " | " + this.getSchedule();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String taskString = super.toString();
        return "[D]" + taskString + " (by: " + this.getSchedule() + ")";
    }
}
