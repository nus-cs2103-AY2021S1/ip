package task;

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

    public Task (String description, LocalDateTime dueDate, boolean done) {
        this.description = description;
        this.status = done ? Status.COMPLETED : Status.INCOMPLETE;
        this.dueDate = dueDate;
    }

    public Task (String description, LocalDateTime dueDate) {
        this.description = description;
        this.status = Status.INCOMPLETE;
        this.dueDate = dueDate;
    }


    public void markDone() {
        this.status = Status.COMPLETED;
    }

    public boolean isDone() { return this.status == Status.COMPLETED; }

    public String writeToFile() {
        return (this.status == Status.COMPLETED ? "1" : "0")
                + "|" + this.description.strip();
    }

    public boolean isDueOn(LocalDate Date) { return this.dueDate.toLocalDate().isEqual(Date); }

    public String getDateString() {
        return this.dueDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm")).toString();
    }

    public String toString() {
        return (this.status == Status.COMPLETED ? "[\u2713]" : "[\u2718]") + this.description;
    }

}
