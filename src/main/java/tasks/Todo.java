package main.java.tasks;
import main.java.exceptions.InvalidDescriptionException;

public class Todo extends Task {

    public Todo(String description) throws InvalidDescriptionException {
        super(description);
        if (description.isBlank()) {
            throw new InvalidDescriptionException("Hey! " +
                    "Todo description shouldn't be blank.");
        }
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String databaseString() {
        return "T | " + super.databaseString();
    }
}
