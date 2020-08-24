import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    enum Status {
        COMPLETED,
        INCOMPLETE
    }

    String description;
    Status status = Status.INCOMPLETE;
    LocalDateTime dueDate;

    public Task (String description, LocalDateTime dueDate) {
        this.description = description;
        this.status = Status.INCOMPLETE;
        this.dueDate = dueDate;
    }

    public void markDone() {
        this.status = Status.COMPLETED;
    }

    public boolean isDone() { return this.status == Status.COMPLETED; }

    public boolean isDueOn(LocalDate Date) { return this.dueDate.toLocalDate().isEqual(Date); }

    public String getDateString() {
        return this.dueDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")).toString();
    }

    public String toString() {
        return (this.status == Status.COMPLETED ? "[\u2718]" : "[\u2713]") + this.description;
    }

}
