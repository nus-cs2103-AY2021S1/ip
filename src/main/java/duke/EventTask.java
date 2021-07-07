package duke;

import java.util.Date;

public class EventTask extends Task {
    private Date time;

    /**
     * @param isDone
     * @param name
     * @param time
     */
    public EventTask(
            Boolean isDone,
            String name,
            Date time
    ) {
        super(isDone, name);
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("[E],%s,%s", super.toString(), time);
    }
}
