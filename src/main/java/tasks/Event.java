package main.java.tasks;
import main.java.exceptions.InvalidDescriptionException;
import main.java.exceptions.InvalidTimeException;

public class Event extends Task {

    private String time;

    public Event(String description, String time) throws InvalidDescriptionException, InvalidTimeException {
        super(description);
        if (description.isBlank()) {
            throw new InvalidDescriptionException("Hey! Event description shouldn't be blank.");
        } else if (time.isBlank()) {
            throw new InvalidTimeException("Do try again by adding a time where the event takes place.");
        } else {
            this.time = time;
        }
    }

    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time + ")";
    }

    @Override
    public String databaseString() {
        return "E | " + super.databaseString() + " | " + this.time;
    }
}
