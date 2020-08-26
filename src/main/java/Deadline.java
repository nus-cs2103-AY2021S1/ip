package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    protected LocalDate date;

    Deadline(String description, LocalDate date) {
        super(description);
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
}
