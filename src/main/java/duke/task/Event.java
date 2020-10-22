package duke.task;

import duke.datetime.DateTimeUtility;

public class Event extends TimedTask {
    public static final String TASK_ICON = "E";
    public Event(String description, String by) {
        super(description, DateTimeUtility.formatString(by));
    }

    @Override
    public String toString() {
        return "["+ this.TASK_ICON +"]" + super.toString() + " (at: " + super.formatBy() + ")";
    }
}
