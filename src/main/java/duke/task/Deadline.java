package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

//    public void editDate(LocalDate newBy) {
//        this.by = newBy;
//    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.getDayOfWeek() + ", "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String saveToFile() {
        return "D/" + super.saveToFile() + "/" + by;
    }
}
