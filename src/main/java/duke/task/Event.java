package duke.task;

import java.util.Date;

public class Event extends Task {
    private Date start;
    private Date end;

    public Event(String description, Date start, Date end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String displayString() {
        return super.displayString() + String.format(" (at: %s)", DateHelper.formatDateRange(start, end));
    }

    @Override
    protected String taskTypeString() {
        return "E";
    }
}
