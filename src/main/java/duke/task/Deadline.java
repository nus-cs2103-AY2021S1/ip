package duke.task;

import java.util.Date;

public class Deadline extends Task {
    private Date date;

    public Deadline(String description, Date date) {
        super(description);
        this.date = date;
    }

    @Override
    public String displayString() {
        return super.displayString() + String.format(" (by: %s)", DateHelper.formatDate(date));
    }

    @Override
    protected String taskTypeString() {
        return "D";
    }
}
