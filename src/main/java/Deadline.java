import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a subclass of task that also keeps track of when a task is due
 * It is initialized via 'deadline (taskname) /by (yyyy-dd-mm)'
 */
class Deadline extends Task {
    private final LocalDate date;

    /**
     * Deadline constructor
     * @param task String representation of task name
     * @param deadline String representation of deadline
     */
    Deadline(String task, String deadline) {
        super(task);
        System.out.println("here");
        this.date = LocalDate.parse(deadline);
        updateRep();
    }

    /**
     * Returns string representation of deadline
     * @return formatted string representation of deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Updates the representation of the deadline object to be saved to the storage file
     */
    @Override
    public void updateRep() {
        super.updateRep();
        this.saveRep = "D%d%" + this.done + "%d%" + this.task + "%d%" + this.date;
    }
}
