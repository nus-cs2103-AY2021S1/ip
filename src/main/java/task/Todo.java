package task;

import exceptions.InvalidDescriptionException;

public class Todo extends Task {
    public Todo(String s) throws InvalidDescriptionException {
        super(s);
        if (s.isBlank()) {
            throw new InvalidDescriptionException("Please add a nice description to your todo :)");
        }
    }

    @Override
    public String formatTaskForDatabase() {
        int status = super.isDone ? 1 : 0;
        return "T|" + status + "|" + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() + super.description;
    }
}
