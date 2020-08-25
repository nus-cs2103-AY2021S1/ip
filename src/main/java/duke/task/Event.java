package duke.task;

import java.util.Date;

public class Event extends Task {
    private Date start;
    private Date end;

    public Event(String description, boolean isDone, Date start, Date end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public Event(String description, Date start, Date end) {
        this(description, false, start, end);
    }

    @Override
    public String displayString() {
        return super.displayString() + String.format(" (at: %s)", DateHelper.formatDateRange(start, end));
    }

    @Override
    protected String taskTypeString() {
        return "E";
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
