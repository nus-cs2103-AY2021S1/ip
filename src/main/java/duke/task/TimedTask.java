package duke.task;


import duke.datetime.DateTimeFormat;
import duke.datetime.DateTimeUtility;

public class TimedTask extends Task {
    protected DateTimeFormat format;
    protected String byString;

    public TimedTask(String description, String by) {
        super(description);
        this.byString = by;
    }

    public void setByString(String byString) {
        this.byString = byString;
    }

    public String formatBy() {
        return DateTimeUtility.formatString(this.byString);
    }

    public String getByString() {
        return byString;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
