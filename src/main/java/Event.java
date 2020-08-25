package main.java;

public class Event extends Task {
    protected String date;
     Event(String description, String date) {
        super(description);
        this.date = date;
    }
    Event (String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = date;
    }
    @Override
    public String getTypeOfTask() {
        return "event";
    }

    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + this.description + " ----- When: " + this.date;
    }
    @Override
    public String getStoreRepresentation() {
        String doneStatus = this.isDone ? "D," : "N,";
        return "E," + doneStatus + this.description + "," + this.date;

    }
}
