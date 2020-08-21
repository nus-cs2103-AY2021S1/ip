import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Optional;

public class Deadline extends Task {

    protected LocalDate date;
    protected Optional<LocalTime> time;

    public Deadline(String desc, LocalDate date) {
        super(desc);
        this.date = date;
        this.time = Optional.empty();
    }

    public Deadline(String desc, LocalDate date, LocalTime time) {
        super(desc);
        this.date = date;
        this.time = Optional.ofNullable(time);
    }

    @Override
    public Task setDone() {
        Task doneTask = this.time.map(localTime -> new Deadline(this.desc, this.date, localTime))
                .orElseGet(() -> new Deadline(this.desc, this.date));
        doneTask.isDone = true;
        return doneTask;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                + (this.time.isPresent() ? this.time.get() : "") + ")";
    }
}
