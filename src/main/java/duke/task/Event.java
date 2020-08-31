package duke.task;

import duke.datetime.DateTimeUtility;

public class Event extends TimedTask {
    public static final String taskIcon = "E";
    public Event(String description, String by) {
        super(description, DateTimeUtility.formatString(by));
    }

    @Override
    public String toString() {
        return "["+ this.taskIcon +"]" + super.toString() + " (at: " + super.formatBy() + ")";
    }
}
