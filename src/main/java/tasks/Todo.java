package main.java.tasks;
import main.java.exceptions.InvalidDescriptionException;

public class Todo extends Task {

    public Todo(String description) throws InvalidDescriptionException {
        super(description);
        if (description.isBlank()) {
            throw new InvalidDescriptionException("Hey! Todo description shouldn't be blank.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
