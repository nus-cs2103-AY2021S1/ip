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

    private String toSavedStringPeriod() {
        return period == null ? periodString : period.toString();
    }

    /**
     * Creates EventTask with array as input. Array size should be exactly 2. First element will be used as description
     * and second element will be used as period.
     *
     * @param arguments The input array.
     * @return An EventTask.
     */
    public static EventTask of(String... arguments) {
        assert(arguments.length == 2);
        return new EventTask(arguments[0], arguments[1]);
    }

    @Override
    public String toSavedString() {
        return String.format("E @@ %s @@ %s @@ %s", toSavedStringIsDone(), desc, toSavedStringPeriod());
    }

    @Override
    public boolean contains(String s) {
        return desc.contains(s) || periodString.contains(s);
    }
}
