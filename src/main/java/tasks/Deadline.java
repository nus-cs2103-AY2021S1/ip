package main.java.tasks;
import main.java.exceptions.InvalidDescriptionException;
import main.java.exceptions.InvalidTimeException;

public class Deadline extends Task {

    private String endTime;

    public Deadline(String description, String endTime) throws InvalidDescriptionException, InvalidTimeException {
        super(description);
        if (description.isBlank()) {
            throw new InvalidDescriptionException("Hey! Deadline description shouldn't be blank.");
        } else if (endTime.isBlank()) {
            throw new InvalidTimeException("Do try again by adding a time you need to get this done by.");
        } else {
            this.endTime = endTime;
        }
    }

    public Deadline(String description, String endTime, boolean isDone) {
        super(description, isDone);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.endTime + ")";
    }

    @Override
    public String databaseString() {
        return "D | " + super.databaseString() + " | " + this.endTime;
    }
}
