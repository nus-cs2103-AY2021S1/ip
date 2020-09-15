package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PeriodTask extends Task {
    protected boolean hasTime = true;
    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs a PeriodTask object when user adds PeriodTask.
     *
     * @param description Title of the task.
     * @param start Start date of the interval.
     * @param end End date of the interval.
     */
    public PeriodTask(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a PeriodTask object when user adds PeriodTask when read from local storage.
     *
     * @param description Title of the task.
     * @param isDone If the task is completed.
     * @param start Start date of the interval.
     * @param end End date of the interval.
     */
    public PeriodTask(String description, int isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public boolean getHasTime() {
        return hasTime;
    }

    public Boolean matchTime(LocalDate date) {
        return start.isEqual(date) || end.isEqual(date);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String startString = start.format(formatter);
        String endString = end.format(formatter);
        return super.toString().replace("[", "[P][") + " (from: " + startString
                + " to: " + endString + " )";
    }

    @Override
    public String getData() {
        return "D" + super.getData() + " | " + start + " | " + end;
    }
}
