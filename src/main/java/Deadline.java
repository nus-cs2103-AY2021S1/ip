package main.java;

class Deadline extends Task {

    final String dateTime;

    Deadline(String description, String datetime) {
        super(description);
        this.dateTime = datetime;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.dateTime + ")";
    }
}
