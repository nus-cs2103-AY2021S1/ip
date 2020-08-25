import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    LocalDate date;

    Event(String task, String duration) {
        super(task);
        this.date = LocalDate.parse(duration);
        updateRep();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public void updateRep() {
        super.updateRep();
        this.saveRep = "E%d%" + this.done + "%d%" + this.task + "%d%" + this.date;
    }
}