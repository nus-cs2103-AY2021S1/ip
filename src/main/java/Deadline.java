package main.java;

class Deadline extends Task {

    final String dateTime;

    Deadline(String description, String datetime) {
        super(description);
        this.dateTime = datetime;
    }

    Deadline(boolean isCompleted, String description, String datetime) {
        super(description);
        this.dateTime = datetime;
        super.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.dateTime + ")";
    }
}
