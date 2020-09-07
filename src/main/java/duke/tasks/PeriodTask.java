package duke.tasks;

import duke.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PeriodTask extends Task{
    protected boolean hasTime = true;
    protected LocalDate start;
    protected LocalDate end;

    public PeriodTask(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public PeriodTask(String description, int isDone, LocalDate start, LocalDate end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }


    public boolean getHasTime() {
        return hasTime;
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
