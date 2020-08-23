package main.java;

class Event extends Task {

    Event(String description, String dateTimeString) {
        super(description, dateTimeString);
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + super.getDateTimeString() + ")";
    }
}
