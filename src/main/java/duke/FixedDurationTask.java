package duke;

public class FixedDurationTask extends Task {
    private final String duration;
    FixedDurationTask(String description, boolean isDone, String duration) {
        super(description, isDone);
        this.duration = duration;
    }
    FixedDurationTask(String description, String duration) {
        this(description, false, duration);
    }

    /**
     * Returns updated task of subtype: FixedDurationTask
     *
     * @param isDone New status for the task
     * @return new FixedDurationTask with updated status
     */
    @Override
    public Task updateStatus(boolean isDone) {
        return updateStatus(isDone, this.duration);
    }

    /**
     * Returns updated task of subtype: FixedDurationTask
     *
     * @param isDone New status for the task
     * @return new FixedDurationTask with updated status
     */
    public Task updateStatus(boolean isDone, String duration) {
        return new FixedDurationTask(super.description, isDone, duration);
    }

    @Override
    public String toString() {
        return "[F]" + super.toString() + " for " + duration + " duration.";
    }
}
