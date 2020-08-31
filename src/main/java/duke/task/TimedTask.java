package duke.task;


import duke.datetime.DateTimeFormat;
import duke.datetime.DateTimeUtility;

public class TimedTask extends Task {
    protected DateTimeFormat format;
    protected String byString;

    public TimedTask(String description, String by) {
        super(description);
        this.byString = by;
        this.format = DateTimeUtility.checkDateTimeType(by);
    }

    public String formatBy() {
        return DateTimeUtility.formatString(this.byString, this.format);
    }

    public String getByString() {
        return byString;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
