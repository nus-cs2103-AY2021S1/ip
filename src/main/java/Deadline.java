package main.java;

import java.time.LocalDate;

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
        return "[D] [" + this.getStatusIcon() + "] " + this.description + " ----- By: " + this.date;
    }
}
