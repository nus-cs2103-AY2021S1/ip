package willy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A type of Task that spans over a period.
 */
public class EventTask extends Task {
    private String stringPeriod;
    private LocalDateTime period;

    public EventTask(String period, String task, TaskSymbol taskType) {
        super(task, taskType);
        this.stringPeriod = period;
        this.period = LocalDateTime.parse(period,
                DateTimeFormatter.
                        ofPattern("dd/MM/yyyy HH:mm"));
    }

    public String getStringPeriod() {
        return stringPeriod;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " +
                period.format(DateTimeFormatter.
                        ofPattern("dd MMM yyyy hh:mm a")) + ")";
    }

}
