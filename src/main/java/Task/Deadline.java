package main.java.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String getTypeOfTask() {
        return "deadline";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd E");
        String dateText = this.date.format(formatter);
        return "[D] [" + this.getStatusIcon() + "] " + this.description + " ----- By: " + dateText;
    }

    @Override
    public String getStoreRepresentation() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String dateText = this.date.format(formatter);
        String doneStatus = this.isDone ? "D," : "N,";
        return "D," + doneStatus + this.description + "," + dateText;

    }
}
