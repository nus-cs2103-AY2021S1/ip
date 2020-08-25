import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class EventTask extends Task {
    String periodString;
    LocalDate period;

    public EventTask(String desc, String periodParse) {
        super(desc);
        try {
            period = LocalDate.parse(periodParse);
            periodString = String.format("%s %d %d",
                    period.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
                    period.getDayOfMonth(),
                    period.getYear());

        } catch (Exception e) {
            // Not parseable
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
                isDone ? 1 : 0, desc, period == null ? periodString : period);
    }
}
