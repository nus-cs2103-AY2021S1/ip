package main.java;

class Event extends Task {

    final String dateTime;

    Event(String description, String datetime) {
        super(description);
        this.dateTime = datetime;
    }

    Event(boolean isCompleted, String description, String datetime) {
        super(isCompleted, description);
        this.dateTime = datetime;
    }

    @Override
    String[] getDataString() {
        return new String[] {"event", String.valueOf(isCompleted), description, dateTime};
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + this.dateTime + ")";
    }
}
