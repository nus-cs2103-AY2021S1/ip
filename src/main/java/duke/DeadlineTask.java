package duke;

import java.util.Date;

public class DeadlineTask extends Task {
    private final Date time;

    /**
     * Create a new deadline task
     * @param isDone
     * @param name
     * @param time
     */
    public DeadlineTask(
            Boolean isDone,
            String name,
            Date time
    ) {
        super(isDone, name);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[D],%s,%s", super.toString(), time);
    }
}
