package main.java;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {
    protected LocalDate date;

    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }
    Event (String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }
    @Override
    public String getTypeOfTask() {
        return "event";
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd E");
        String dateText = this.date.format(formatter);
        return "[E] [" + this.getStatusIcon() + "] " + this.description + " ----- When: " + dateText;
    }
    @Override
    public String getStoreRepresentation() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String dateText = this.date.format(formatter);
        String doneStatus = this.isDone ? "D," : "N,";
        return "E," + doneStatus + this.description + "," + dateText;

    }
}
