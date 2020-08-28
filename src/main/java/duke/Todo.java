package duke;

/**
 * Represents an {@code Todo} object. Inherits from {@code Task} object
 */
class Todo extends Task {

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
