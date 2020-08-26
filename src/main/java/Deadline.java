package main.java;

public class Deadline extends Task {
    protected String date;

     Deadline(String description, String date) {
        super(description);
        this.date = date;
    }
    Deadline (String description, String date, boolean isDone) {
        super(description, isDone);
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

    @Override
    public String getStoreRepresentation() {
        String doneStatus = this.isDone ? "D," : "N,";
        return "D," + doneStatus + this.description + "," + this.date;

    }
}
