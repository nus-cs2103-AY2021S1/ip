package main.java;

class Deadline extends Task {

    final String dateTime;

    Deadline(String description, String datetime) {
        super(description);
        this.dateTime = datetime;
    }

    Deadline(boolean isCompleted, String description, String datetime) {
        super(isCompleted, description);
        this.dateTime = datetime;
    }

    @Override
    String[] getDataString() {
        return new String[] {"deadline", String.valueOf(isCompleted), description, dateTime};
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.dateTime + ")";
    }
}
