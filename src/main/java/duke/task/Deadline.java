package duke.task;

import java.util.Date;

public class Deadline extends Task {
    private Date date;

    public Deadline(String description, boolean isDone, Date date) {
        super(description, isDone);
        this.date = date;
    }

    public Deadline(String description, Date date) {
        this(description, false, date);
    }

    @Override
    public String displayString() {
        return super.displayString() + String.format(" (by: %s)", DateHelper.formatDate(date));
    }

    @Override
    protected String taskTypeString() {
        return "D";
    }

    public Date getDate() {
        return date;
    }
}
