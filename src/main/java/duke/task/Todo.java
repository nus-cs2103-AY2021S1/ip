package duke.task;

import java.util.Optional;

public class Todo extends Task {
    public static final String TODO_SYMBOL = "T";
    public static final int COMMAND_LENGTH = 1;

    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    public static Todo createTodo(String description) {
        return new Todo(description, false);
    }

    @Override
    public String toString() {
        return "[" + TODO_SYMBOL + "]" + toStringSuffix();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Todo) {
            Todo otherTodo = (Todo) other;
            return this.isEqual(otherTodo);
        }
        return false;
    }

    @Override
    public String getTaskSymbol() {
        return TODO_SYMBOL;
    }

    @Override
    public Optional<String> getTaskDatetime() {
        return Optional.empty();
    }
}
