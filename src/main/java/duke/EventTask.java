package duke;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class EventTask extends Task {
    String periodString;
    LocalDate period;

    /**
     * Initialise description to be desc and period to be periodParse. Will attempt to parse deadlineParse.
     * If it is in the format yyyy-mm-dd, it will be stored as local date, for nicer string representation later.
     *
     * @param desc Description of the task.
     * @param periodParse The deadline of the task.
     */
    public EventTask(String desc, String periodParse) {
        super(desc);
        try {
            period = LocalDate.parse(periodParse);
            periodString = String.format("%s %d %d",
                    period.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                    period.getDayOfMonth(),
                    period.getYear());

        } catch (Exception e) {
            // Not parsable
            periodString = periodParse;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()  + " (at: " + periodString + ")";
    }

    @Override
    public String toSaveString() {
        return String.format("E @@ %d @@ %s @@ %s",
                isDone ? 1 : 0, desc, period == null ? periodString : period.toString());
    }

    @Override
    public boolean contains(String s) {
        return desc.contains(s) || periodString.contains(s);
    }
}
