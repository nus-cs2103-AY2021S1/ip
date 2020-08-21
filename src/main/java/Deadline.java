import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Optional;

public class Deadline extends Task {

    protected LocalDate date;
    protected Optional<LocalTime> time;

    public Deadline(String desc, boolean isDone, LocalDate date) {
        super(desc, isDone);
        this.date = date;
        this.time = Optional.empty();
    }

    public Deadline(String desc, boolean isDone, LocalDate date, LocalTime time) {
        super(desc, isDone);
        this.date = date;
        this.time = Optional.ofNullable(time);
    }

    @Override
    public Task setDone() {
        Task doneTask = this.time.map(localTime -> new Deadline(this.desc, this.isDone, this.date, localTime))
                .orElseGet(() -> new Deadline(this.desc, this.isDone, this.date));
        doneTask.isDone = true;
        return doneTask;
    }

    @Override
    public String formatTask() {
        return ("D" + " | " + (isDone ? "V" : "X") + " | " + desc + " | "
                + this.date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")) + " "
                + this.time.map(localTime -> localTime.format(DateTimeFormatter.ofPattern("HHmm")))
                .orElse(""));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + (this.time.isPresent() ? this.time.get() : "") + ")";
    }
}
