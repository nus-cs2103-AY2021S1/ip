package duke.task;

import duke.datetime.DateTimeUtility;

public class Deadline extends TimedTask {
    public static final String TASK_ICON = "D";
    public Deadline(String description, String by) {
        super(description, DateTimeUtility.formatString(by));
    }

    @Override
    public String toString() {
        return "["+ this.TASK_ICON +"]" + super.toString() + " (by: " + super.formatBy() + ")";
    }
}
