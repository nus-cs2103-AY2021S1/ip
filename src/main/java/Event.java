import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    private final LocalDate date;

    /**
     * Event constructor
     * @param task String representation of task name
     * @param duration String representation of duration
     */
    Event(String task, String duration) {
        super(task);
        this.date = LocalDate.parse(duration);
        updateRep();
    }

    /**
     * Returns string representation of event
     * @return formatted string representation of event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Updates the representation of the event object to be saved to the storage file
     */
    @Override
    public void updateRep() {
        super.updateRep();
        this.saveRep = "E%d%" + this.done + "%d%" + this.task + "%d%" + this.date;
    }
}
