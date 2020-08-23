package main.java;

class Deadline extends Task {

    Deadline(String description, String dateTimeString) {
        super(description, dateTimeString);
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + super.getDateTimeString() + ")";
    }
}
