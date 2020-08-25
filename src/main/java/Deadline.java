import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    LocalDate date;

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