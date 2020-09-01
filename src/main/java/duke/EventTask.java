package duke;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class EventTask extends Task {
    private String periodString;
    private LocalDate period;

    /**
     * Initialises description to be desc and period to be periodParse. Will attempt to parse deadlineParse.
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
        return "[E]" + super.toString() + " (at: " + periodString + ")";
    }

    private String toSaveStringPeriod() {
        return period == null ? periodString : period.toString();
    }

    @Override
    public String toSaveString() {
        return String.format("E @@ %s @@ %s @@ %s", toSaveStringDone(), desc, toSaveStringPeriod());
    }

    @Override
    public boolean contains(String s) {
        return desc.contains(s) || periodString.contains(s);
    }
}
