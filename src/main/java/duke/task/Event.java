package duke.task;

import duke.format.DateFormat;

import java.util.Date;

public class Event extends Task {
    protected Date at;



    public Event(String name, boolean isComplete, Date at) {
        super(name, isComplete, TaskType.EVENT);
        this.at = at;
    }

    @Override
    public Date getDate() {
        return this.at;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", super.toString(), DateFormat.formatDate(this.at));
    }
}
