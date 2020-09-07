import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a subclass of task that also keeps track of when a task is due
 * It is initialized via 'deadline (taskname) /by (yyyy-dd-mm)'
 */
class Deadline extends Task {
    private LocalDate date;

    Deadline(String task, String deadline) {
        super(task);
        this.date = LocalDate.parse(deadline);
        updateRep();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public void updateRep() {
        super.updateRep();
        this.saveRep = "D%d%" + this.done + "%d%" + this.task + "%d%" + this.date;
    }
}