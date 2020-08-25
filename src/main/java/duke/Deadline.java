package duke;

import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;

import java.util.Optional;

public class Deadline extends Task {

    private LocalDate date;
    private Optional<LocalTime> time;

    public Deadline(String desc, LocalDate date, boolean isDone) {
        super(desc, isDone);
        this.date = date;
        this.time = Optional.empty();
    }

    public Deadline(String desc, LocalDate date, LocalTime time, boolean isDone) {
        super(desc, isDone);
        this.date = date;
        this.time = Optional.ofNullable(time);
    }

    @Override
    public Task setDone() {
        Task doneTask = this.time.map(
                localTime -> new Deadline(this.desc, this.date, localTime, this.isDone))
                .orElseGet(() -> new Deadline(this.desc, this.date, this.isDone));
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
